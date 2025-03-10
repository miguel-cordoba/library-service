package com.miguelcordoba.LibraryService.api;

import com.miguelcordoba.LibraryService.dto.LoanDTO;
import com.miguelcordoba.LibraryService.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private LoanDTO sampleLoan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleLoan = new LoanDTO(1L, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(14));
    }

    @Test
    void testCreateLoan() {
        when(loanService.createLoan(sampleLoan)).thenReturn(sampleLoan);

        ResponseEntity<LoanDTO> response = loanController.createLoan(sampleLoan);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleLoan, response.getBody());
    }

    @Test
    void testGetAllLoans() {
        when(loanService.getAllLoans()).thenReturn(List.of(sampleLoan));

        ResponseEntity<List<LoanDTO>> response = loanController.getAllLoans();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(sampleLoan, response.getBody().get(0));
    }

    @Test
    void testGetLoanById_Found() {
        when(loanService.getLoanById(1L)).thenReturn(sampleLoan);

        ResponseEntity<LoanDTO> response = loanController.getLoanById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleLoan, response.getBody());
    }

    @Test
    void testGetLoanById_NotFound() {
        when(loanService.getLoanById(1L)).thenReturn(null);

        ResponseEntity<LoanDTO> response = loanController.getLoanById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateLoan_Found() {
        when(loanService.updateLoan(eq(1L), any(LoanDTO.class))).thenReturn(sampleLoan);

        ResponseEntity<LoanDTO> response = loanController.updateLoan(1L, sampleLoan);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleLoan, response.getBody());
    }

    @Test
    void testUpdateLoan_NotFound() {
        when(loanService.updateLoan(eq(1L), any(LoanDTO.class))).thenReturn(null);

        ResponseEntity<LoanDTO> response = loanController.updateLoan(1L, sampleLoan);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteLoan_Found() {
        when(loanService.deleteLoan(1L)).thenReturn(true);

        ResponseEntity<Void> response = loanController.deleteLoan(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteLoan_NotFound() {
        when(loanService.deleteLoan(1L)).thenReturn(false);

        ResponseEntity<Void> response = loanController.deleteLoan(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
