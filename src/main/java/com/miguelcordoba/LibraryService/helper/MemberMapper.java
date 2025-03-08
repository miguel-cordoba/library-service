package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.MemberDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Member;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMapper {
    public static MemberDTO toDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getUsername(),
                member.getEmail(),
                member.getAddress(),
                member.getPhoneNumber()
        );
    }

    public static Member toEntity(MemberDTO memberDTO) {
        return new Member(
                memberDTO.id(),
                memberDTO.username(),
                memberDTO.email(),
                memberDTO.address(),
                memberDTO.phoneNumber(),
                null  // assuming loans are handled separately
        );
    }

    public static List<MemberDTO> toDTOList(List<Member> members) {
        return members.stream()
                .map(MemberMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Member> toEntityList(List<MemberDTO> memberDTOs) {
        return memberDTOs.stream()
                .map(MemberMapper::toEntity)
                .collect(Collectors.toList());
    }
}
