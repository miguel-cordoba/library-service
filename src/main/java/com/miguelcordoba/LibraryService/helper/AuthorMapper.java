package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;
import com.miguelcordoba.LibraryService.dto.NestedAuthorDTO;
import com.miguelcordoba.LibraryService.dto.NestedBookDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class AuthorMapper {

    public AuthorDTO mapToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getDateOfBirth(),  mapBookEntitySetToDTOSet(author.getBooks()));
    }

    public Author mapToEntity(AuthorDTO authorDTO) {
        //Because of the circularity we need to first create the Author entity without books and then map the books separately.
        Author author = new Author(authorDTO.id(), authorDTO.name(), authorDTO.dateOfBirth(), new HashSet<>()); // Empty books set first
        Set<Book> books = mapNestedBookDTOSetToEntitySet(authorDTO.books(), author); // Now pass author
        author.setBooks(books);

        return author;
    }

    public NestedAuthorDTO mapToNestedDTO(Author author) {
        return new NestedAuthorDTO(
                author.getId(),
                author.getName(),
                author.getDateOfBirth()
        );
    }
    //we return the nested version to avoid circular reference
    public Set<NestedBookDTO> mapBookEntitySetToDTOSet(Set<Book> books) {
        return books.stream()
                .map(book -> new NestedBookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getPrice()
                ))
                .collect(Collectors.toSet());
    }

    public Set<Book> mapNestedBookDTOSetToEntitySet(Set<NestedBookDTO> nestedBookDTOs, Author author) {
        return nestedBookDTOs.stream()
                .map(nestedBookDTO -> new Book(
                        nestedBookDTO.id(),
                        nestedBookDTO.title(),
                        nestedBookDTO.genre(),
                        nestedBookDTO.price(),
                        author
                ))
                .collect(Collectors.toSet());
    }


}



