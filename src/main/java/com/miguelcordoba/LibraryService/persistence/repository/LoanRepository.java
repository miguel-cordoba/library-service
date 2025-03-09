package com.miguelcordoba.LibraryService.persistence.repository;

import com.miguelcordoba.LibraryService.persistence.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByMemberId(Long memberId);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.memberId = :memberId AND (l.returnDate IS NULL OR l.returnDate >= CURRENT_DATE)")
    long countActiveLoansByMemberId(Long memberId);}
