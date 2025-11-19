package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.HairCheckupService;
import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.core.utilities.results.Result;
import com.heysmile.heysmileapi.core.utilities.results.SuccessDataResult;
import com.heysmile.heysmileapi.core.utilities.results.SuccessResult;
import com.heysmile.heysmileapi.dtos.haircheckup.response.HairCheckupResponseDto;
import com.heysmile.heysmileapi.entities.HairCheckup;
import com.heysmile.heysmileapi.entities.Image;
import com.heysmile.heysmileapi.entities.User;
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
    private final UserService userService;

    public HairCheckupController(HairCheckupService hairCheckupService, UserService userService) {
        this.hairCheckupService = hairCheckupService;
        this.userService = userService;
    }

    @GetMapping("/my-checkups")
    public ResponseEntity<SuccessDataResult<List<HairCheckupResponseDto>>> getMyCheckups() {
        User currentUser = userService.getAuthenticatedUserEntity();
        var result = hairCheckupService.getUserCheckups(currentUser);
        return ResponseEntity.ok(new SuccessDataResult<>(result, "User checkups listed", HttpStatus.OK));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Result> createCheckup(
            @RequestPart(value = "userNotes", required = false) String userNotes,
            @RequestPart(value = "frontImage") MultipartFile frontImage,
            @RequestPart(value = "backImage") MultipartFile backImage,
            @RequestPart(value = "leftImage") MultipartFile leftImage,
            @RequestPart(value = "rightImage") MultipartFile rightImage,
            @RequestPart(value = "topImage") MultipartFile topImage
    ) {
        User currentUser = userService.getAuthenticatedUserEntity();

        HairCheckup checkup = new HairCheckup();
        checkup.setUser(currentUser);
        checkup.setUserNotes(userNotes);

        checkup.setImageFront(createImageFromMultipart(frontImage, "front"));
        checkup.setImageBack(createImageFromMultipart(backImage, "back"));
        checkup.setImageLeft(createImageFromMultipart(leftImage, "left"));
        checkup.setImageRight(createImageFromMultipart(rightImage, "right"));
        checkup.setImageTop(createImageFromMultipart(topImage, "top"));

        hairCheckupService.createCheckup(checkup);
        return ResponseEntity.ok(new SuccessResult("Hair checkup submitted successfully", HttpStatus.CREATED));
    }

    private Image createImageFromMultipart(MultipartFile file, String suffix) {
        try {
            if (file == null || file.isEmpty()) return null;

            Image image = new Image();
            image.setName(UUID.randomUUID() + "_" + suffix);
            image.setType(file.getContentType());
            image.setData(file.getBytes());
            image.setUrl(image.getName()); // URL mantığına göre name set ediliyor
            return image;
        } catch (IOException e) {
            throw new RuntimeException("Error processing image file", e);
        }
    }
}