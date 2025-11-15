package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.AuthService;
import com.heysmile.heysmileapi.core.constants.AuthMessages;
import com.heysmile.heysmileapi.core.utilities.results.Result;
import com.heysmile.heysmileapi.core.utilities.results.SuccessResult;
import com.heysmile.heysmileapi.dtos.auth.request.RegisterRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {


    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping(
            value = "/register",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }
    )
    public ResponseEntity<Result> register(
            @RequestPart("user") @Valid RegisterRequestDto registerRequestDto,
            @RequestPart(value = "profilePhoto", required = false) Optional<MultipartFile> profilePhoto
    ){
        authService.register(registerRequestDto, profilePhoto);
        return ResponseEntity.ok(new SuccessResult(AuthMessages.REGISTER_SUCCESS, HttpStatus.CREATED));
    }






}
