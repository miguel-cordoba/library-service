package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.LoanDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Loan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    public LoanDTO toDTO(Loan loan) {
        return new LoanDTO(
                loan.getId(),
                loan.getMemberId(),
                loan.getBookId(),
                loan.getLendDate(),
                loan.getReturnDate()
        );
    }

    public Loan toEntity(LoanDTO loanDTO) {
        return new Loan(
                loanDTO.id(),
                loanDTO.memberId(),
                loanDTO.bookId(),
                loanDTO.lendDate(),
                loanDTO.returnDate()
        );
    }

    public List<LoanDTO> toDTOList(List<Loan> loans) {
        return loans.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Loan> toEntityList(List<LoanDTO> loanDTOs) {
        return loanDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
