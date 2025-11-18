package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.ImageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/images")
public class ImagesController {

    private final ImageService imageService;


    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/{url}")
    public byte[] getImageDataByUrl(@PathVariable String url){
        return imageService.getImageDataByUrl(url);
    }
}
