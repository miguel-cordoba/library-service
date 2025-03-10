package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.AuthorSummaryDTO;
import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import com.miguelcordoba.LibraryService.persistence.repository.AuthorRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    public BookMapper(@Lazy AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    // Existing method to map Book to BookDTO
    public BookDTO mapToDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getPrice(),
                new AuthorSummaryDTO(book.getAuthor().getId(), book.getAuthor().getName()) // Avoid recursion
        );
    }

    // Method to map BookDTO to Book entity
    public Book mapToEntity(BookDTO bookDTO) {
        return new Book(
                bookDTO.id(),
                bookDTO.title(),
                bookDTO.genre(),
                bookDTO.price(),
                authorMapper.mapToEntity(authorMapper.mapToDTO(bookDTO.author()) // Full AuthorMapper for entity conversion
        ));
    }

    public Set<BookDTO> mapToDTOSet(Set<Book> books) {
        return books.stream()
                .map(this::mapToDTO) // Use existing mapToDTO method for each book
                .collect(Collectors.toSet()); // Collect into a Set
    }
}
