package com.miguelcordoba.LibraryService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

public record AuthorDTO(
        Long id, //id is JPA-autogenerated, so we won´t validate it

        @NotBlank(message = "First name cannot be blank")
        String name,

        @NotBlank(message = "Last name cannot be blank")
        LocalDate dateOfBirth,

        Set<BookDTO> books
) {}
