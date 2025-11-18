package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.ImageService;
import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.core.exceptions.NotFoundException;
import com.heysmile.heysmileapi.dataAccess.ImageDao;
import com.heysmile.heysmileapi.entities.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ImageManager implements ImageService {

    private final ImageDao imageDao;
    private final UserService userService;


    public ImageManager(ImageDao imageDao, UserService userService) {
        this.imageDao = imageDao;
        this.userService = userService;
    }

    @Override
    public Image uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is null or empty");
        }

        try {
            Image imageToSave = Image.builder()
                    .type(file.getContentType())
                    .name(file.getOriginalFilename())
                    .data(file.getBytes())
                    .url(generateUrl())
                    .build();

            return imageDao.save(imageToSave);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Image> getAllImages() {
        return imageDao.findAll();
    }

    @Override
    public Image getImageById(UUID id) {
        return imageDao.findById(id).orElseThrow(() -> new NotFoundException("Image not found"));
    }

    @Override
    public void deleteImage(UUID id) {
        Image image = getImageById(id);
        imageDao.delete(image);
    }

    @Override
    public Image getImageByUrl(String url) {
        return imageDao.findByUrl(url).orElseThrow(() -> new NotFoundException("Image not found"));
    }

    private String generateUrl() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[128];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }
}
