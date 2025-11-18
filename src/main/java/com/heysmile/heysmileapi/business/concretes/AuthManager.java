package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.AuthService;
import com.heysmile.heysmileapi.business.abstracts.ImageService;
import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.core.security.JwtService;
import com.heysmile.heysmileapi.dtos.auth.request.LoginRequestDto;
import com.heysmile.heysmileapi.dtos.auth.request.RegisterRequestDto;
import com.heysmile.heysmileapi.dtos.auth.response.LoginResponseDto;
import com.heysmile.heysmileapi.entities.Role;
import com.heysmile.heysmileapi.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthManager implements AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final ImageService imageService;

    private final JwtService jwtService;


    public AuthManager(UserService userService, PasswordEncoder passwordEncoder, ImageService imageService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public void register(RegisterRequestDto registerRequestDto, Optional<MultipartFile> profilePhoto) {

        if (userService.checkIfUserExistsByMail(registerRequestDto.getEmail())) {
            throw new SecurityException("User with email "+registerRequestDto.getEmail()+" already exists");
        }


        User user = new User();
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setDateOfBirth(registerRequestDto.getDateOfBirth());
        user.setEmail(registerRequestDto.getEmail());
        user.setPhoneNumber(registerRequestDto.getPhoneNumber());
        user.setAuthorities(Set.of(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        if (profilePhoto.isPresent()){
            var uploadedImage = imageService.uploadImage(profilePhoto.get());
            user.setProfileImage(uploadedImage);
        }

        userService.createUser(user);

    }

    @Override
    public LoginResponseDto login(LoginRequestDto authLoginRequestDto) {
        if (!userService.checkIfUserExistsByMail(authLoginRequestDto.getEmail())) {
            throw new SecurityException("User with email "+authLoginRequestDto.getEmail()+" does not exist");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginRequestDto.getEmail(), authLoginRequestDto.getPassword()));

        if (authentication.isAuthenticated()){
            var user = (User) userService.loadUserByUsername(authLoginRequestDto.getEmail());
            var token = jwtService.generateToken(authLoginRequestDto.getEmail(), user.getAuthorities());
            return new LoginResponseDto(token);
        }else {
            throw new SecurityException("Invalid username or password");
        }



    }
}
