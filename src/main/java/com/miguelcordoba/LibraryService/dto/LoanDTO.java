package com.miguelcordoba.LibraryService.dto;

import java.time.LocalDate;

public record LoanDTO(Long loanId,
                      Long memberId,
                      String bookTitle,
                      LocalDate loanDate,
                      LocalDate returnDate) {
}
