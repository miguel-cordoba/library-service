package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.exception.ResourceNotFoundException;
import com.miguelcordoba.LibraryService.helper.AuthorMapper;
import com.miguelcordoba.LibraryService.helper.BookMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import com.miguelcordoba.LibraryService.persistence.repository.AuthorRepository;
import com.miguelcordoba.LibraryService.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper,AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::mapToDTO);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Author author;
        Book book = null;
        Book savedBook;
        //Check if the author is in the db or direct the user to create it first
        if (bookDTO.getAuthor() != null || bookDTO.getAuthor().id() != null) {
            // Find the author by ID or throw an exception if not found
            author = authorRepository.findById(bookDTO.getAuthor().id())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found. Please add the author first."));

            // Map the bookDTO to a Book entity, passing the author
            book = bookMapper.mapToEntity(bookDTO, author);
            savedBook = bookRepository.save(book);

            return bookMapper.mapToDTO(savedBook);
        }

        throw new ResourceNotFoundException("Author not found. Please add the author first.");

        //TODO: Add creation of author + following update call of author.books after book is created. For Now strict control. Author comes first

        // Convert saved entity back to DTO
       // return bookMapper.mapToDTO(savedBook);
    }

    @Override
    public Optional<BookDTO> updateBook(Long id, BookDTO bookDTO) {
        Optional<BookDTO> updatedBook =  bookRepository.findById(id)
                .map(existingDoc -> {
                    //id, title and author will not change
                    existingDoc.setGenre(bookDTO.getGenre());
                    existingDoc.setPrice(bookDTO.getPrice());
                    return bookRepository.save(existingDoc);
                })
                .map(bookMapper::mapToDTO);

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
