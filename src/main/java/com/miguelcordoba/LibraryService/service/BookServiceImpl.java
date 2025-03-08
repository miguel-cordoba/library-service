package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.helper.AuthorMapper;
import com.miguelcordoba.LibraryService.helper.BookMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import com.miguelcordoba.LibraryService.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(BookMapper::mapToDTO);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        // Convert DTO to entity and save
        Book book = BookMapper.mapToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        // Convert saved entity back to DTO
        return BookMapper.mapToDTO(savedBook);
    }

    @Override
    public Optional<BookDTO> updateBook(Long id, BookDTO bookDTO) {
        Optional<BookDTO> updatedBook =  bookRepository.findById(id)
                .map(existingDoc -> {
                    existingDoc.setGenre(bookDTO.genre());
                    existingDoc.setAuthor(AuthorMapper.mapToEntity(bookDTO.author()));
                    return bookRepository.save(existingDoc);
                })
                .map(BookMapper::mapToDTO);

        return updatedBook;
    }

    @Override
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
