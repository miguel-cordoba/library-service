package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.LoanDTO;
import com.miguelcordoba.LibraryService.helper.LoanMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import com.miguelcordoba.LibraryService.persistence.entity.Loan;
import com.miguelcordoba.LibraryService.persistence.entity.Member;
import com.miguelcordoba.LibraryService.persistence.repository.BookRepository;
import com.miguelcordoba.LibraryService.persistence.repository.LoanRepository;
import com.miguelcordoba.LibraryService.persistence.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final LoanMapper loanMapper;

    public LoanServiceImpl(LoanRepository loanRepository, MemberRepository memberRepository, BookRepository bookRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.loanMapper = loanMapper;
    }

    // Create a new loan
    @Override
    public LoanDTO createLoan(LoanDTO loanDTO) {
        // Validate if Member exists
        Member member = memberRepository.findById(loanDTO.memberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Validate if Book exists
        Book book = bookRepository.findById(loanDTO.bookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if member already has 5 loans
        long loanCount = loanRepository.countActiveLoansByMemberId(loanDTO.memberId());
        if (loanCount >= 5) {
            throw new RuntimeException("Member has already reached the maximum number of 5 loans");
        }

        // Create the loan using the validated memberId and bookId
        Loan loan = new Loan();
        loan.setMemberId(member.getId());
        loan.setBookId(book.getId());
        loan.setLendDate(loanDTO.lendDate());
        loan.setReturnDate(loanDTO.returnDate());

        return loanMapper.toDTO(loanRepository.save(loan));
    }


    // Get all loans
    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream()
                    .map(loan -> new LoanDTO(loan.getId(), loan.getMemberId(), loan.getBookId(), loan.getLendDate(), loan.getReturnDate()))
                    .collect(Collectors.toList());
    }

    // Get loan by ID
    @Override
    public LoanDTO getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        return loan != null ? new LoanDTO(loan.getId(), loan.getMemberId(), loan.getBookId(), loan.getLendDate(), loan.getReturnDate()) : null;
    }

    // Update an existing loan
    @Override
    public LoanDTO updateLoan(Long loanId, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan != null) {
            loan.setBookId(loanDTO.bookId());
            loan.setLendDate(loanDTO.lendDate());
            loan.setReturnDate(loanDTO.returnDate());
            loanRepository.save(loan);
            return new LoanDTO(loan.getId(), loan.getMemberId(), loan.getBookId(), loan.getLendDate(), loan.getReturnDate());
        }
        return null;
    }

    // Delete a loan
    @Override
    public boolean deleteLoan(Long loanId) {
        if (loanRepository.existsById(loanId)) {
            loanRepository.deleteById(loanId);
            return true;
        }
        return false;
    }

    // Count active loans for a member
    @Override
    public long countActiveLoansByMemberId(Long memberId) {
        return loanRepository.countActiveLoansByMemberId(memberId);
    }
}
