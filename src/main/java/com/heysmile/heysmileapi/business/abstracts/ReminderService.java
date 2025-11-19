package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.dtos.reminder.request.CreateReminderRequestDto;
import com.heysmile.heysmileapi.dtos.reminder.response.CreateReminderResponseDto;
import com.heysmile.heysmileapi.dtos.reminder.response.GetReminderResponseDto;

import java.util.List;
import java.util.UUID;

public interface ReminderService {
    CreateReminderResponseDto createReminder(CreateReminderRequestDto createReminderRequestDto);

    List<GetReminderResponseDto> getRemindersForAuthenticatedUser();

    void deleteReminder(UUID id);
}