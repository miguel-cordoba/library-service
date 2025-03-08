package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;
import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorDTO mapToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getDateOfBirth(),  mapBookEntitySetToDTOSet(author.getBooks()));
    }

    public static Author mapToEntity(AuthorDTO authorDTO) {
        return new Author(authorDTO.id(), authorDTO.name(), authorDTO.dateOfBirth(), mapBookDTOSetToEntitySet(authorDTO.books()));
    }

    private static Set<BookDTO> mapBookEntitySetToDTOSet(Set<Book> books) {
        return books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getPrice(),
                        mapToDTO(book.getAuthor())
                ))
                .collect(Collectors.toSet());
    }

    public static Set<Book> mapBookDTOSetToEntitySet(Set<BookDTO> bookDTOs) {
        return bookDTOs.stream()
                .map(bookDTO -> new Book(
                        bookDTO.id(),
                        bookDTO.title(),
                        bookDTO.genre(),
                        bookDTO.price(),
                        mapToEntity(bookDTO.author()) // Directly passing the existing Author reference
                ))
                .collect(Collectors.toSet());
    }

}



