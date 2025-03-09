package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.LoanDTO;

import java.util.List;

public interface LoanService {

    LoanDTO createLoan(LoanDTO loanDTO);

    List<LoanDTO> getAllLoans();

    LoanDTO getLoanById(Long loanId);

    LoanDTO updateLoan(Long loanId, LoanDTO loanDTO);

    boolean deleteLoan(Long loanId);

    long countActiveLoansByMemberId(Long memberId);
}
