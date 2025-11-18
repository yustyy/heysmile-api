package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.core.exceptions.NotFoundException;
import com.heysmile.heysmileapi.dataAccess.UserDao;
import com.heysmile.heysmileapi.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
