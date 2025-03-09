package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.MemberDTO;
import com.miguelcordoba.LibraryService.helper.MemberMapper;
import com.miguelcordoba.LibraryService.persistence.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream().map(memberMapper::toDTO).toList();
    }

    @Override
    public Optional<MemberDTO> getMemberById(Long id) {
        return memberRepository.findById(id).map(memberMapper::toDTO);
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        return memberMapper.toDTO(memberRepository.save(memberMapper.toEntity(memberDTO)));
    }

    @Override
    public Optional<MemberDTO> updateMember(Long id, MemberDTO memberDTO) {
        return memberRepository.findById(id).map(existingMember -> {
            existingMember.setUsername(memberDTO.username());
            existingMember.setEmail(memberDTO.email());
            existingMember.setAddress(memberDTO.address());
            existingMember.setPhoneNumber(memberDTO.phoneNumber());
            return memberMapper.toDTO(memberRepository.save(existingMember));
        });
    }

    @Override
    public boolean deleteMember(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
