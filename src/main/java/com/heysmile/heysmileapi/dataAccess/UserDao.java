package com.heysmile.heysmileapi.dataAccess;

import com.heysmile.heysmileapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {

}
