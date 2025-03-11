package com.miguelcordoba.LibraryService.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record BookDTO(
        Long id,

        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotBlank(message = "Genre cannot be blank")
        String genre,

        @NotBlank(message = "Price cannot be blank")
        Double price,

        @Nullable
        NestedAuthorDTO author // This allows book creation without forcing author details

) {

}
