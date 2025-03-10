package com.miguelcordoba.LibraryService.api;

import com.miguelcordoba.LibraryService.dto.MemberDTO;
import com.miguelcordoba.LibraryService.service.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberControllerTest {

    @Mock
    private MemberServiceImpl memberService;

    @InjectMocks
    private MemberController memberController;

    private MemberDTO memberDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        memberDTO = new MemberDTO(1L, "testuser", "test@example.com", "123 Street", "123456789");
    }

    @Test
    void getAllMembers_ShouldReturnListOfMembers() {
        when(memberService.getAllMembers()).thenReturn(List.of(memberDTO));
        ResponseEntity<List<MemberDTO>> response = memberController.getAllMembers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getMemberById_WhenFound_ShouldReturnMember() {
        when(memberService.getMemberById(1L)).thenReturn(Optional.of(memberDTO));
        ResponseEntity<MemberDTO> response = memberController.getMemberById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(memberDTO, response.getBody());
    }

    @Test
    void getMemberById_WhenNotFound_ShouldReturnNotFound() {
        when(memberService.getMemberById(1L)).thenReturn(Optional.empty());
        ResponseEntity<MemberDTO> response = memberController.getMemberById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createMember_ShouldReturnCreatedMember() {
        when(memberService.createMember(any(MemberDTO.class))).thenReturn(memberDTO);
        ResponseEntity<MemberDTO> response = memberController.createMember(memberDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(memberDTO, response.getBody());
    }

    @Test
    void updateMember_WhenFound_ShouldReturnUpdatedMember() {
        when(memberService.updateMember(eq(1L), any(MemberDTO.class))).thenReturn(Optional.of(memberDTO));
        ResponseEntity<MemberDTO> response = memberController.updateMember(1L, memberDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(memberDTO, response.getBody());
    }

    @Test
    void updateMember_WhenNotFound_ShouldReturnNotFound() {
        when(memberService.updateMember(eq(1L), any(MemberDTO.class))).thenReturn(Optional.empty());
        ResponseEntity<MemberDTO> response = memberController.updateMember(1L, memberDTO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteMember_WhenFound_ShouldReturnNoContent() {
        when(memberService.deleteMember(1L)).thenReturn(true);
        ResponseEntity<Void> response = memberController.deleteMember(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteMember_WhenNotFound_ShouldReturnNotFound() {
        when(memberService.deleteMember(1L)).thenReturn(false);
        ResponseEntity<Void> response = memberController.deleteMember(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
