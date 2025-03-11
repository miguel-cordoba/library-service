package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;

    public BookMapper(AuthorMapper authorMapper){
        this.authorMapper = authorMapper;
    }

    //TODO: Unit Tests
    public BookDTO mapToDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getPrice(),
                authorMapper.mapToNestedDTO(book.getAuthor())
        );
    }

    public Book mapToEntity(BookDTO bookDTO, Author author) {
        return new Book(
                bookDTO.id(),
                bookDTO.title(),
                bookDTO.genre(),
                bookDTO.price(),
                author
        );


    }

}

