package com.miguelcordoba.LibraryService.dto;

public record NestedBookDTO(Long id, String title, String genre, Double price) {
    //We are using this class to encapsulate the AuthorDTO within the AuthorDTO
    // without causing circular dependencies.
}
