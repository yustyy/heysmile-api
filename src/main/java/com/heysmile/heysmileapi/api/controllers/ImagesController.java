package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.ImageService;
import com.heysmile.heysmileapi.entities.Image;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/images")
public class ImagesController {

    private final ImageService imageService;


    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/{url}")
    public ResponseEntity<byte[]> getImageDataByUrl(@PathVariable String url){
        Image imageData = imageService.getImageByUrl(url);

        MediaType mediaType = MediaTypeFactory
                .getMediaType(imageData.getName())
                .orElse(MediaType.IMAGE_PNG);

        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(imageData.getData());
    }
}
