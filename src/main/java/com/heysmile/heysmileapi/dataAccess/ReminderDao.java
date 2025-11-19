package com.heysmile.heysmileapi.dataAccess;

import com.heysmile.heysmileapi.entities.Reminder;
import com.heysmile.heysmileapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReminderDao extends JpaRepository<Reminder, UUID> {
    List<Reminder> findAllByCalendar_User(User user);

}
