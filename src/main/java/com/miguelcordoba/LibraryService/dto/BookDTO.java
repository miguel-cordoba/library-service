package com.miguelcordoba.LibraryService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record BookDTO(
        Long id,

        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotBlank(message = "Genre cannot be blank")
        String genre,

        @NotBlank(message = "Price cannot be blank")
        Double price,

        @NotEmpty(message = "Author must not be empty")
        AuthorDTO author

) {}
