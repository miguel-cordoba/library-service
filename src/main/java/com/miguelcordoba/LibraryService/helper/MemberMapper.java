package com.miguelcordoba.LibraryService.helper;

import com.miguelcordoba.LibraryService.dto.MemberDTO;
import com.miguelcordoba.LibraryService.persistence.entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMapper {
    public MemberDTO toDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getUsername(),
                member.getEmail(),
                member.getAddress(),
                member.getPhoneNumber()
        );
    }

    public Member toEntity(MemberDTO memberDTO) {
        return new Member(
                memberDTO.id(),
                memberDTO.username(),
                memberDTO.email(),
                memberDTO.address(),
                memberDTO.phoneNumber());
    }

    public List<MemberDTO> toDTOList(List<Member> members) {
        return members.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Member> toEntityList(List<MemberDTO> memberDTOs) {
        return memberDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
