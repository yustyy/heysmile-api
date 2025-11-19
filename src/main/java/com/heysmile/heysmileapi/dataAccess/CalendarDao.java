package com.heysmile.heysmileapi.dataAccess;

import com.heysmile.heysmileapi.entities.Calendar;
import com.heysmile.heysmileapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CalendarDao extends JpaRepository<Calendar, UUID> {
    List<Calendar> findAllByUserOrderByDateAsc(User user);

}
