package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;
import com.miguelcordoba.LibraryService.dto.NestedAuthorDTO;
import com.miguelcordoba.LibraryService.dto.NestedBookDTO;
import com.miguelcordoba.LibraryService.helper.AuthorMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    private AuthorMapper authorMapper;

    @BeforeEach
    void setUp() {
        authorMapper = new AuthorMapper();
    }

    @Test
    void testMapToDTO() {
        Author author = new Author(1L, "J.K. Rowling", LocalDate.of(1965, 7, 31), new HashSet<>());
        Book book = new Book(1L, "Harry Potter", "Fantasy", 20.99, author);
        author.setBooks(Set.of(book));

        AuthorDTO authorDTO = authorMapper.mapToDTO(author);

        assertEquals(author.getId(), authorDTO.id());
        assertEquals(author.getName(), authorDTO.name());
        assertEquals(author.getDateOfBirth(), authorDTO.dateOfBirth());
        assertEquals(1, authorDTO.books().size());
        assertEquals(book.getTitle(), authorDTO.books().iterator().next().title());
    }

    @Test
    void testMapToEntity() {
        AuthorDTO authorDTO = new AuthorDTO(1L, "J.K. Rowling", LocalDate.of(1965, 7, 31), new HashSet<>());
        NestedBookDTO bookDTO = new NestedBookDTO(1L, "Harry Potter", "Fantasy", 20.99);
        authorDTO.books().add(bookDTO);

        Author author = authorMapper.mapToEntity(authorDTO);

        assertEquals(authorDTO.id(), author.getId());
        assertEquals(authorDTO.name(), author.getName());
        assertEquals(authorDTO.dateOfBirth(), author.getDateOfBirth());
        assertEquals(1, author.getBooks().size());
        assertEquals(bookDTO.title(), author.getBooks().iterator().next().getTitle());
    }

    @Test
    void testMapToNestedDTO() {
        Author author = new Author(1L, "J.K. Rowling", LocalDate.of(1965, 7, 31), new HashSet<>());
        NestedAuthorDTO nestedAuthorDTO = authorMapper.mapToNestedDTO(author);

        assertEquals(author.getId(), nestedAuthorDTO.id());
        assertEquals(author.getName(), nestedAuthorDTO.name());
        assertEquals(author.getDateOfBirth(), nestedAuthorDTO.dateOfBirth());
    }

    @Test
    void testMapNestedBookDTOSetToEntitySet() {
        NestedBookDTO bookDTO = new NestedBookDTO(1L, "Harry Potter", "Fantasy", 20.99);
        Set<NestedBookDTO> nestedBookDTOSet = new HashSet<>();
        nestedBookDTOSet.add(bookDTO);

        Author author = new Author();
        Set<Book> books = authorMapper.mapNestedBookDTOSetToEntitySet(nestedBookDTOSet, author);

        assertEquals(1, books.size());
        Book book = books.iterator().next();
        assertEquals(bookDTO.title(), book.getTitle());
        assertEquals(bookDTO.genre(), book.getGenre());
        assertEquals(bookDTO.price(), book.getPrice());
        assertEquals(author, book.getAuthor());
    }

    @Test
    void testMapBookEntitySetToDTOSet() {
        Author author = new Author(1L, "J.K. Rowling", LocalDate.of(1965, 7, 31), new HashSet<>());
        Book book = new Book(1L, "Harry Potter", "Fantasy", 20.99, author);
        Set<Book> books = new HashSet<>();
        books.add(book);

        Set<NestedBookDTO> nestedBookDTOSet = authorMapper.mapBookEntitySetToDTOSet(books);

        assertEquals(1, nestedBookDTOSet.size());
        NestedBookDTO nestedBookDTO = nestedBookDTOSet.iterator().next();
        assertEquals(book.getTitle(), nestedBookDTO.title());
        assertEquals(book.getGenre(), nestedBookDTO.genre());
        assertEquals(book.getPrice(), nestedBookDTO.price());
    }
}
