package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.dtos.calendar.response.CalendarResponseDto;
import com.heysmile.heysmileapi.entities.Calendar;
import com.heysmile.heysmileapi.entities.User;

import java.util.List;
import java.util.UUID;

public interface CalendarService {
    Calendar createCalendar(Calendar calendar);
    List<CalendarResponseDto> getUserCalendars(User user);
    void deleteCalendar(UUID id);

}