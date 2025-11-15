package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.AuthService;
import com.heysmile.heysmileapi.business.abstracts.ImageService;
import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.dtos.auth.request.RegisterRequestDto;
import com.heysmile.heysmileapi.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class AuthManager implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthManager.class);

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final ImageService imageService;


    public AuthManager(UserService userService, PasswordEncoder passwordEncoder, ImageService imageService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    @Override
    public void register(RegisterRequestDto registerRequestDto, Optional<MultipartFile> profilePhoto) {
        logger.info("Registering new user, email: {}", registerRequestDto.getEmail());

        User user = new User();
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setDateOfBirth(registerRequestDto.getDateOfBirth());
        user.setEmail(registerRequestDto.getEmail());
        user.setPhoneNumber(registerRequestDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        if (profilePhoto.isPresent()){
            logger.info("Uploading profile photo for user: {}", registerRequestDto.getEmail());
            var uploadedImage = imageService.uploadImage(profilePhoto.get());
            user.setProfileImage(uploadedImage);
        }

        userService.createUser(user);

        logger.info("User registered successfully: {}", registerRequestDto.getEmail());


    }
}
