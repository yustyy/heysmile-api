package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.dtos.auth.request.RegisterRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface AuthService {
    void register(RegisterRequestDto registerRequestDto, Optional<MultipartFile> profilePhoto);
}
