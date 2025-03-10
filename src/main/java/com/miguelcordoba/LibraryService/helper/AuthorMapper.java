package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;
import com.miguelcordoba.LibraryService.dto.AuthorSummaryDTO;
import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import com.miguelcordoba.LibraryService.service.AuthorService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    private final BookMapper bookMapper;
    private final AuthorService authorService;

    @Autowired
    public AuthorMapper(@Lazy BookMapper bookMapper, AuthorService authorService) {
        this.bookMapper = bookMapper;
        this.authorService = authorService;
    }


        public AuthorDTO mapToDTO(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getDateOfBirth(),
                bookMapper.mapToDTOSet(author.getBooks())
        );
    }

    public AuthorDTO mapToDTO(AuthorSummaryDTO authorSummaryDTO) {
        // Assuming you can fetch the full author by ID
        return authorService.getAuthorById(authorSummaryDTO.id());

    }

    public Author mapToEntity(AuthorDTO authorDTO) {
        return new Author(
                authorDTO.id(),
                authorDTO.name(),
                authorDTO.dateOfBirth(),
                mapBookDTOSetToEntitySet(authorDTO.books())
        );
    }

    public AuthorSummaryDTO mapToAuthorSummaryDTO(Author author) {
        return new AuthorSummaryDTO(author.getId(), author.getName()); // A separate method to handle Author summaries
    }
    private Set<BookDTO> mapBookEntitySetToDTOSet(Set<Book> books) {
        return books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getPrice(),
                        mapToAuthorSummaryDTO(book.getAuthor())
                ))
                .collect(Collectors.toSet());
    }
    public Set<Book> mapBookDTOSetToEntitySet(Set<BookDTO> bookDTOs) {
        return bookDTOs.stream()
                .map(bookDTO -> new Book(
                        bookDTO.id(),
                        bookDTO.title(),
                        bookDTO.genre(),
                        bookDTO.price(),
                        mapToEntity(authorService.getAuthorById(bookDTO.author().id())
                )))
                .collect(Collectors.toSet());
    }

}
