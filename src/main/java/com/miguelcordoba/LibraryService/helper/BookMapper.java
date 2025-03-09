package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    //TODO: Unit Tests
    public BookDTO mapToDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getPrice(),
                AuthorMapper.mapToDTO(book.getAuthor())
        );
    }

    public static Book mapToEntity(BookDTO bookDTO) {
        return new Book(
                bookDTO.id(),
                bookDTO.title(),
                bookDTO.genre(),
                bookDTO.price(),
                AuthorMapper.mapToEntity(bookDTO.author())
        );


    }

}

