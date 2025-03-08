package com.miguelcordoba.LibraryService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record BookDTO(
        Long id,

        @NotBlank(message = "Title cannot be blank")
        @Size(max = 255, message = "Title must be less than 255 characters")
        String title,

        @NotBlank(message = "Genre cannot be blank")
        @Size(max = 255, message = "Genre must be less than 255 characters")
        String genre,

        @NotBlank(message = "Price cannot be blank")
        Double price,

        @NotEmpty(message = "Author must not be empty")
        AuthorDTO author

) {}
