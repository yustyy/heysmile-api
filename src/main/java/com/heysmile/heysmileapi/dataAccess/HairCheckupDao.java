package com.heysmile.heysmileapi.dataAccess;

import com.heysmile.heysmileapi.entities.HairCheckup;
import com.heysmile.heysmileapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public interface HairCheckupDao extends JpaRepository<HairCheckup, UUID> {
    List<HairCheckup> findAllByUser(User user);
}
