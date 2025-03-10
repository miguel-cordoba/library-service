package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorDTO getAuthorById(Long id);

    AuthorDTO createAuthor(AuthorDTO author);

    List<AuthorDTO> getAllAuthors();

    Optional<AuthorDTO> updateAuthor(Long id, AuthorDTO author);

    boolean deleteAuthor(Long id);
}
