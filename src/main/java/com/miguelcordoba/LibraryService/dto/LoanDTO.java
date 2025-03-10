package com.miguelcordoba.LibraryService.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record LoanDTO(
        Long id,

        @NotNull(message = "Member ID cannot be null")
        Long memberId,

        @NotNull(message = "Book ID cannot be null")
        Long bookId,

        @NotNull(message = "Lend date cannot be null")
        @FutureOrPresent(message = "Lend date must be today or in the future")
        LocalDate lendDate,

        LocalDate returnDate
) {}
