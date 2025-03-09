package com.miguelcordoba.LibraryService.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Table(name = "loans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long bookId;

    @Column(nullable = false)
    private LocalDate lendDate;

    @Column
    private LocalDate returnDate;

    public Loan(Long memberId, Long bookId, LocalDate lendDate, LocalDate localDate1) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }
}
