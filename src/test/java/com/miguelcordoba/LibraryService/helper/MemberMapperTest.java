package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.MemberDTO;
import com.miguelcordoba.LibraryService.helper.MemberMapper;
import com.miguelcordoba.LibraryService.persistence.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {

    private MemberMapper memberMapper;

    @BeforeEach
    void setUp() {
        memberMapper = new MemberMapper();
    }

    @Test
    void testToDTO() {
        Member member = new Member(1L, "john_doe", "john@example.com", "1234 Elm Street", "1234567890");
        MemberDTO memberDTO = memberMapper.toDTO(member);
        assertEquals(member.getId(), memberDTO.id());
        assertEquals(member.getUsername(), memberDTO.username());
        assertEquals(member.getEmail(), memberDTO.email());
        assertEquals(member.getAddress(), memberDTO.address());
        assertEquals(member.getPhoneNumber(), memberDTO.phoneNumber());
    }

    @Test
    void testToEntity() {
        MemberDTO memberDTO = new MemberDTO(1L, "john_doe", "john@example.com", "1234 Elm Street", "1234567890");
        Member member = memberMapper.toEntity(memberDTO);
        assertEquals(memberDTO.id(), member.getId());
        assertEquals(memberDTO.username(), member.getUsername());
        assertEquals(memberDTO.email(), member.getEmail());
        assertEquals(memberDTO.address(), member.getAddress());
        assertEquals(memberDTO.phoneNumber(), member.getPhoneNumber());
    }

    @Test
    void testToDTOList() {
        Member member1 = new Member(1L, "john_doe", "john@example.com", "1234 Elm Street", "1234567890");
        Member member2 = new Member(2L, "jane_doe", "jane@example.com", "5678 Oak Avenue", "0987654321");
        List<MemberDTO> memberDTOs = memberMapper.toDTOList(List.of(member1, member2));
        assertEquals(2, memberDTOs.size());
        assertEquals(member1.getId(), memberDTOs.get(0).id());
        assertEquals(member2.getId(), memberDTOs.get(1).id());
    }

    @Test
    void testToEntityList() {
        MemberDTO memberDTO1 = new MemberDTO(1L, "john_doe", "john@example.com", "1234 Elm Street", "1234567890");
        MemberDTO memberDTO2 = new MemberDTO(2L, "jane_doe", "jane@example.com", "5678 Oak Avenue", "0987654321");
        List<Member> members = memberMapper.toEntityList(List.of(memberDTO1, memberDTO2));
        assertEquals(2, members.size());
        assertEquals(memberDTO1.id(), members.get(0).getId());
        assertEquals(memberDTO2.id(), members.get(1).getId());
    }
}
