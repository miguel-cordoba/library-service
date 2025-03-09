package com.miguelcordoba.LibraryService.dto;

import java.time.LocalDate;

public record LoanDTO(Long id,
                      Long memberId,
                      Long bookId,
                      LocalDate lendDate,
                      LocalDate returnDate) {
}
