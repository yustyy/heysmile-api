package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.core.exceptions.NotFoundException;
import com.heysmile.heysmileapi.dataAccess.UserDao;
import com.heysmile.heysmileapi.dtos.user.response.MeResponseDto;
import com.heysmile.heysmileapi.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Set;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;


    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public boolean checkIfUserExistsByMail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public User getAuthenticatedUserEntity() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if (authentication.getPrincipal().equals("anonymousUser")) {
           throw new RuntimeException("User not found");
       }

       return getByEmail(authentication.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public MeResponseDto getAuthenticatedUser() {
        User user = getAuthenticatedUserEntity();

        MeResponseDto meResponseDto = new MeResponseDto();
        meResponseDto.setId(user.getId());
        meResponseDto.setFirstName(user.getFirstName());
        meResponseDto.setLastName(user.getLastName());
        meResponseDto.setEmail(user.getEmail());
        meResponseDto.setPhoneNumber(user.getPhoneNumber());
        meResponseDto.setDateOfBirth(user.getDateOfBirth());
        if (user.getProfileImage() != null){
            meResponseDto.setProfileImageUrl("https://hey-smile-api.yusufacmaci.com/api/images/"+user.getProfileImage().getUrl());
        }
        return meResponseDto;
    }

    @Override
    public User getAuthenticatedUserReference() {
        User user = getAuthenticatedUserEntity();
        return userDao.getReferenceById(user.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
