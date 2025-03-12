package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.LoanDTO;
import com.miguelcordoba.LibraryService.helper.LoanMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanMapperTest {

    private LoanMapper loanMapper;

    @BeforeEach
    void setUp() {
        loanMapper = new LoanMapper();
    }

    @Test
    void testToDTO() {
        Loan loan = new Loan(1L, 101L, 201L, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 10));

        LoanDTO loanDTO = loanMapper.toDTO(loan);

        assertEquals(loan.getId(), loanDTO.id());
        assertEquals(loan.getMemberId(), loanDTO.memberId());
        assertEquals(loan.getBookId(), loanDTO.bookId());
        assertEquals(loan.getLendDate(), loanDTO.lendDate());
        assertEquals(loan.getReturnDate(), loanDTO.returnDate());
    }

    @Test
    void testToEntity() {
        LoanDTO loanDTO = new LoanDTO(1L, 101L, 201L, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 10));

        Loan loan = loanMapper.toEntity(loanDTO);

        assertEquals(loanDTO.id(), loan.getId());
        assertEquals(loanDTO.memberId(), loan.getMemberId());
        assertEquals(loanDTO.bookId(), loan.getBookId());
        assertEquals(loanDTO.lendDate(), loan.getLendDate());
        assertEquals(loanDTO.returnDate(), loan.getReturnDate());
    }

    @Test
    void testToDTOList() {
        Loan loan1 = new Loan(1L, 101L, 201L, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 10));
        Loan loan2 = new Loan(2L, 102L, 202L, LocalDate.of(2023, 3, 2), LocalDate.of(2023, 3, 11));

        List<LoanDTO> loanDTOs = loanMapper.toDTOList(List.of(loan1, loan2));

        assertEquals(2, loanDTOs.size());
        assertEquals(loan1.getId(), loanDTOs.get(0).id());
        assertEquals(loan2.getId(), loanDTOs.get(1).id());
    }

    @Test
    void testToEntityList() {
        LoanDTO loanDTO1 = new LoanDTO(1L, 101L, 201L, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 10));
        LoanDTO loanDTO2 = new LoanDTO(2L, 102L, 202L, LocalDate.of(2023, 3, 2), LocalDate.of(2023, 3, 11));

        List<Loan> loans = loanMapper.toEntityList(List.of(loanDTO1, loanDTO2));

        assertEquals(2, loans.size());
        assertEquals(loanDTO1.id(), loans.get(0).getId());
        assertEquals(loanDTO2.id(), loans.get(1).getId());
    }
}
