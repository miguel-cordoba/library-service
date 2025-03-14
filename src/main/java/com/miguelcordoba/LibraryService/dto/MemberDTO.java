package com.miguelcordoba.LibraryService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record MemberDTO(Long id,
                        @NotEmpty
                        String username,
                        @Email
                        @NotEmpty
                        String email,
                        @NotEmpty
                        String address,
                        @NotEmpty
                        String phoneNumber) {}

