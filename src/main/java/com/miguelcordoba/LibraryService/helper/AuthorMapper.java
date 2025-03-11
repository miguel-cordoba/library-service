package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;
import com.miguelcordoba.LibraryService.dto.NestedAuthorDTO;
import com.miguelcordoba.LibraryService.dto.NestedBookDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class AuthorMapper {

    public AuthorDTO mapToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getDateOfBirth(),  mapBookEntitySetToDTOSet(author.getBooks()));
    }

    public Author mapToEntity(AuthorDTO authorDTO) {
        return new Author(authorDTO.id(), authorDTO.name(), authorDTO.dateOfBirth());
    }

    public NestedAuthorDTO mapToNestedDTO(Author author) {
        return new NestedAuthorDTO(
                author.getId(),
                author.getName(),
                author.getDateOfBirth()
        );
    }
    //we return the nested version to avoid circular reference
    private Set<NestedBookDTO> mapBookEntitySetToDTOSet(Set<Book> books) {
        return books.stream()
                .map(book -> new NestedBookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getPrice()
                ))
                .collect(Collectors.toSet());
    }


}



