package com.miguelcordoba.LibraryService.dto;

import java.time.LocalDate;


public record NestedAuthorDTO (
     Long id,
     String name,
     LocalDate dateOfBirth)
{
    //We are using this class to encapsulate the AuthorDTO within the BookDTO
    // without causing circular dependencies.
}
