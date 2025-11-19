package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.CalendarService;
import com.heysmile.heysmileapi.business.abstracts.ReminderService;
import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.core.exceptions.NotFoundException;
import com.heysmile.heysmileapi.dataAccess.CalendarDao;
import com.heysmile.heysmileapi.dataAccess.ReminderDao;
import com.heysmile.heysmileapi.dtos.reminder.request.CreateReminderRequestDto;
import com.heysmile.heysmileapi.dtos.reminder.response.CreateReminderResponseDto;
import com.heysmile.heysmileapi.dtos.reminder.response.GetReminderResponseDto;
import com.heysmile.heysmileapi.entities.Calendar;
import com.heysmile.heysmileapi.entities.CalendarCategory;
import com.heysmile.heysmileapi.entities.Reminder;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReminderManager implements ReminderService {

    private final ReminderDao reminderDao;
    private final CalendarService calendarService;
    private final UserService userService;

    public ReminderManager(ReminderDao reminderDao, CalendarService calendarService, UserService userService) {
        this.reminderDao = reminderDao;
        this.calendarService = calendarService;
        this.userService = userService;
    }


    @Override
    @Transactional
    public CreateReminderResponseDto createReminder(CreateReminderRequestDto createReminderRequestDto) {
        var authenticatedUserReference = userService.getAuthenticatedUserReference();


        Reminder reminder = new Reminder();
        reminder.setTitle(createReminderRequestDto.getTitle() != null ? createReminderRequestDto.getTitle() : null);
        reminder.setContent(createReminderRequestDto.getContent());


        Calendar calendar = new Calendar();
        calendar.setUser(authenticatedUserReference);
        calendar.setCategory(CalendarCategory.REMINDER);
        calendar.setDate(createReminderRequestDto.getDate());
        calendar.setReminder(reminder);

        calendarService.createCalendar(calendar);

        Reminder savedReminder = reminderDao.save(reminder);

        CreateReminderResponseDto responseDto = new CreateReminderResponseDto();
        responseDto.setId(savedReminder.getId());
        responseDto.setTitle(savedReminder.getTitle());
        responseDto.setContent(savedReminder.getContent());
        responseDto.setDate(savedReminder.getCalendar().getDate());
        return responseDto;

    }

    @Override
    @Transactional
    public List<GetReminderResponseDto> getRemindersForAuthenticatedUser() {
        var authenticatedUserReference = userService.getAuthenticatedUserReference();

        return reminderDao.findAllByCalendar_User(authenticatedUserReference)
                .stream()
                .map(x -> {
                    GetReminderResponseDto reminder = new GetReminderResponseDto();
                    reminder.setId(x.getId());
                    reminder.setTitle(x.getTitle());
                    reminder.setContent(x.getContent());
                    reminder.setDate(x.getCalendar().getDate());
                    return reminder;
                })
                .toList();
    }

}
