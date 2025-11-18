package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    Image uploadImage(MultipartFile multipartFile);

    List<Image> getAllImages();

    Image getImageById(UUID id);

    void deleteImage(UUID id);

    Image getImageByUrl(String url);
}
