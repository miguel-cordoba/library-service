package com.miguelcordoba.LibraryService.persistence.repository;

import com.miguelcordoba.LibraryService.persistence.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
