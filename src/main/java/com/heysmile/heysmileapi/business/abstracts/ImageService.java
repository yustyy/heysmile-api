package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.entities.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image uploadImage(MultipartFile multipartFile);
}
