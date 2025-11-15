package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.ImageService;
import com.heysmile.heysmileapi.entities.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageManager implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageManager.class);


    @Override
    public Image uploadImage(MultipartFile multipartFile) {
        //upload image
        logger.info("Uplaod image..... //not uploading any lol");

        return null;
    }
}
