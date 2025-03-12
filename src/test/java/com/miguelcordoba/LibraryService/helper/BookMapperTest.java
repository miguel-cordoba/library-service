package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.dto.NestedAuthorDTO;
import com.miguelcordoba.LibraryService.helper.AuthorMapper;
import com.miguelcordoba.LibraryService.helper.BookMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    private BookMapper bookMapper;
    private AuthorMapper authorMapper;

    @BeforeEach
    void setUp() {
        authorMapper = new AuthorMapper();
        bookMapper = new BookMapper(authorMapper);
    }

    @Test
    void testMapToDTO() {
        // Use LocalDate.of() to create date for author
        Author author = new Author(1L, "J.K. Rowling", LocalDate.of(1965, 7, 31), Set.of());
        Book book = new Book(1L, "Harry Potter", "Fantasy", 20.99, author);

        // Map Book to BookDTO
        BookDTO bookDTO = bookMapper.mapToDTO(book);

        // Assertions to verify if the DTO is correct
        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getGenre(), bookDTO.getGenre());
        assertEquals(book.getPrice(), bookDTO.getPrice());
        assertNotNull(bookDTO.getAuthor());  // Check that author is mapped
        assertEquals(author.getName(), bookDTO.getAuthor().name());
    }

    @Test
    void testMapToEntity() {
        // Use LocalDate.of() to create date for author
        Author author = new Author(1L, "J.K. Rowling", LocalDate.of(1965, 7, 31), Set.of());
        BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "Fantasy", 20.99, new NestedAuthorDTO(1L, "J.K. Rowling", LocalDate.of(1965, 7, 31)));

        // Map BookDTO to Book entity
        Book book = bookMapper.mapToEntity(bookDTO, author);

        // Assertions to verify if the Book entity is correctly created
        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getGenre(), bookDTO.getGenre());
        assertEquals(book.getPrice(), bookDTO.getPrice());
        assertNotNull(book.getAuthor());  // Check that author is correctly set
        assertEquals(author.getName(), book.getAuthor().getName());
    }
}
