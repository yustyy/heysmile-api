package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.HairCheckupService;
import com.heysmile.heysmileapi.core.utilities.results.SuccessDataResult;
import com.heysmile.heysmileapi.dtos.haircheckup.request.CreateHairCheckupRequestDto;
import com.heysmile.heysmileapi.dtos.haircheckup.response.HairCheckupResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/hair-checkups")
public class HairCheckupController {

    private final HairCheckupService hairCheckupService;

    public HairCheckupController(HairCheckupService hairCheckupService) {
        this.hairCheckupService = hairCheckupService;
    }


    @PostMapping(
            value = "/",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }
    )
    public ResponseEntity<SuccessDataResult> createHairCheckup(
            @RequestPart("hairCheckup") CreateHairCheckupRequestDto createHairCheckupRequestDto,
            @RequestPart(value = "imageFront", required = false) MultipartFile imageFront,
            @RequestPart(value = "imageBack", required = false) MultipartFile imageBack,
            @RequestPart(value = "imageLeft", required = false) MultipartFile imageLeft,
            @RequestPart(value = "imageRight", required = false) MultipartFile imageRight,
            @RequestPart(value = "imageTop", required = false) MultipartFile imageTop
    ) throws IOException {

        var result = hairCheckupService.createCheckup(
                imageFront, imageBack, imageLeft, imageRight, imageTop,
                createHairCheckupRequestDto.getUserNotes()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessDataResult(result, "Hair checkup created successfully", HttpStatus.CREATED));
    }


    @GetMapping("/me")
    public ResponseEntity<SuccessDataResult<List<HairCheckupResponseDto>>> getMyHairCheckups() {

        var result = hairCheckupService.getAuthenticatedUserCheckups();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessDataResult<>(result, "User hair checkups retrieved successfully", HttpStatus.OK));
    }



}