package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.dtos.reminder.request.CreateReminderRequestDto;
import com.heysmile.heysmileapi.dtos.reminder.response.CreateReminderResponseDto;
import com.heysmile.heysmileapi.dtos.reminder.response.GetReminderResponseDto;

import java.util.List;

public interface ReminderService {
    CreateReminderResponseDto createReminder(CreateReminderRequestDto createReminderRequestDto);

    List<GetReminderResponseDto> getRemindersForAuthenticatedUser();
}