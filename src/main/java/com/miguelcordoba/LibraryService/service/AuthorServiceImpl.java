package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;
import com.miguelcordoba.LibraryService.dto.BookDTO;
import com.miguelcordoba.LibraryService.exception.AuthorNotFoundException;
import com.miguelcordoba.LibraryService.helper.AuthorMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.repository.AuthorRepository;
import com.miguelcordoba.LibraryService.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
    public AuthorServiceImpl(@Lazy AuthorRepository authorRepository,@Lazy BookRepository bookRepository,@Lazy AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDTO getAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));

        Set<BookDTO> books = bookRepository.findByAuthorId(authorId)
                .stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getGenre(), book.getPrice(), authorMapper.mapToAuthorSummaryDTO(author)))
                .collect(Collectors.toSet());

        return new AuthorDTO(author.getId(), author.getName(), author.getDateOfBirth(), books);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        // Convert DTO to Entity
        Author entityAuthor = authorMapper.mapToEntity(authorDTO);
        // Save the Entity
        Author savedAuthor = authorRepository.save(entityAuthor);
        // Convert saved Entity back to DTO and return
        return authorMapper.mapToDTO(savedAuthor);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::mapToDTO) // Convert Entity to DTO
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDTO> updateAuthor(Long id, AuthorDTO authorDTO) {
        Optional<AuthorDTO> updatedAuthor = authorRepository.findById(id)
                .map(existingAuthor -> {
                    // Update fields with the new data from the DTO
                    existingAuthor.setName(authorDTO.name());
                    existingAuthor.setDateOfBirth(authorDTO.dateOfBirth());
                    // Map BookDTO back to Book entity
                    existingAuthor.setBooks(authorMapper.mapBookDTOSetToEntitySet(authorDTO.books()));
                    // Save the updated entity
                    return authorRepository.save(existingAuthor);
                })
                .map(authorMapper::mapToDTO); // Convert updated entity to DTO

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
