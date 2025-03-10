package com.miguelcordoba.LibraryService.dto;

import java.time.LocalDate;
import java.util.Set;

public record AuthorDTO(Long id, String name, LocalDate dateOfBirth, Set<BookDTO> books) {}
