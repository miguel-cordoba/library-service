package com.miguelcordoba.LibraryService.service;

import com.miguelcordoba.LibraryService.dto.MemberDTO;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<MemberDTO> getAllMembers();
    Optional<MemberDTO> getMemberById(Long id);
    MemberDTO createMember(MemberDTO memberDTO);
    Optional<MemberDTO> updateMember(Long id, MemberDTO memberDTO);
    boolean deleteMember(Long id);
}
