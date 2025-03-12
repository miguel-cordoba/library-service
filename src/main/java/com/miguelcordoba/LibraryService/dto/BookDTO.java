package com.miguelcordoba.LibraryService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
        private Long id;
        private String title;
        private String genre;
        private Double price;
        private NestedAuthorDTO author;
}