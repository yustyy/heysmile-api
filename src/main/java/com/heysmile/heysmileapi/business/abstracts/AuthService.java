package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.dtos.auth.request.LoginRequestDto;
import com.heysmile.heysmileapi.dtos.auth.request.RegisterRequestDto;
import com.heysmile.heysmileapi.dtos.auth.response.LoginResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface AuthService {
    void register(RegisterRequestDto registerRequestDto, Optional<MultipartFile> profilePhoto);

    LoginResponseDto login(LoginRequestDto authLoginRequestDto);
}
