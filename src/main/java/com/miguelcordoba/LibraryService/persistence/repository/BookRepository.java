package com.miguelcordoba.LibraryService.persistence.repository;

import com.miguelcordoba.LibraryService.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom query to delete documents associated with a specific author
    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE EXISTS (SELECT 1 FROM b.author a WHERE a.id = :authorId)")
    void deleteByAuthorId(Long authorId);

    List<Book> findByAuthorId(Long authorId);


}
