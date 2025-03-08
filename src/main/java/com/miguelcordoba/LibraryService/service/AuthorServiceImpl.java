package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.AuthorDTO;
import com.miguelcordoba.LibraryService.helper.AuthorMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Author;
import com.miguelcordoba.LibraryService.persistence.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(Long id) {
        return authorRepository.findById(id).map(AuthorMapper::mapToDTO);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author entityAuthor = AuthorMapper.mapToEntity(authorDTO);
        Author savedAuthor = authorRepository.save(entityAuthor);
        return AuthorMapper.mapToDTO(savedAuthor);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDTO> updateAuthor(Long id, AuthorDTO authorDTO) {
        Optional<AuthorDTO> updatedAuthor =  authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(authorDTO.name());
                    existingAuthor.setDateOfBirth(authorDTO.dateOfBirth());
                    existingAuthor.setBooks(AuthorMapper.mapBookDTOSetToEntitySet(authorDTO.books()));
                    return authorRepository.save(existingAuthor);
                })
                .map(AuthorMapper::mapToDTO);

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