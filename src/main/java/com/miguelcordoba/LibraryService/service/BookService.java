package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(Long id);

    BookDTO createBook(BookDTO documentDTO);

    Optional<BookDTO> updateBook(Long id, BookDTO documentDTO);

    boolean deleteBook(Long id);
}
