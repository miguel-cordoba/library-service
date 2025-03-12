package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;
import com.miguelcordoba.LibraryService.helper.AuthorMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.entity.Book;
import com.miguelcordoba.LibraryService.persistence.repository.AuthorRepository;
import com.miguelcordoba.LibraryService.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(Long id) {
        return authorRepository.findById(id).map(authorMapper::mapToDTO);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author entityAuthor = authorMapper.mapToEntity(authorDTO);
        Author savedAuthor = authorRepository.save(entityAuthor);
        return authorMapper.mapToDTO(savedAuthor);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDTO> updateAuthor(Long id, AuthorDTO authorDTO) {
        //TODO: fix key duplication
        Optional<AuthorDTO> updatedAuthor = authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(authorDTO.name());
                    existingAuthor.setDateOfBirth(authorDTO.dateOfBirth());
                    Set<Book> bookSet = authorMapper.mapNestedBookDTOSetToEntitySet(authorDTO.books(), existingAuthor);
                    bookSet.stream().forEach(book -> {book.setId(null);
                        System.out.println("Book ID before save: " + book.getId()); // Should print null
                        bookRepository.save(book);
                                            });
                    //bookRepository.saveAll(bookSet);
                    return authorRepository.save(existingAuthor);
                })
                .map(authorMapper::mapToDTO);

        return updatedAuthor;
    }

    @Override
    public boolean deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}