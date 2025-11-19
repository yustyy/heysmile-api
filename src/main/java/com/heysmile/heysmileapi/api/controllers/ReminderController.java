package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.ReminderService;
import com.heysmile.heysmileapi.core.utilities.results.SuccessDataResult;
import com.heysmile.heysmileapi.core.utilities.results.SuccessResult;
import com.heysmile.heysmileapi.dtos.reminder.request.CreateReminderRequestDto;
import com.heysmile.heysmileapi.dtos.reminder.response.CreateReminderResponseDto;
import com.heysmile.heysmileapi.dtos.reminder.response.GetReminderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;


    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }



    @PostMapping("/")
    public ResponseEntity<SuccessDataResult<CreateReminderResponseDto>> createReminder(@RequestBody CreateReminderRequestDto createReminderRequestDto){

        var result = reminderService.createReminder(createReminderRequestDto);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessDataResult<>(result, "Reminder created successfully", HttpStatus.CREATED));



    }

    @GetMapping("/me")
    public ResponseEntity<SuccessDataResult<List<GetReminderResponseDto>>> getMyReminders() {

        var result = reminderService.getRemindersForAuthenticatedUser();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessDataResult<>(result, "Reminders retrieved successfully", HttpStatus.OK));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResult> deleteReminder(@PathVariable UUID id) {

        reminderService.deleteReminder(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResult("Reminder deleted successfully", HttpStatus.OK));

    }
}
