package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.core.utilities.results.SuccessDataResult;
import com.heysmile.heysmileapi.dtos.user.response.MeResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/me")
    public ResponseEntity<SuccessDataResult<MeResponseDto>> getAuthenticatedUser(){
        var result = userService.getAuthenticatedUser();

        return ResponseEntity.ok(new SuccessDataResult<>(result, "Authenticated user retrieved successfully", org.springframework.http.HttpStatus.OK));
    }
}
