package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(User user);

    User getByEmail(String email);

    boolean checkIfUserExistsByMail(String email);


    User getAuthenticatedUserEntity();


}
