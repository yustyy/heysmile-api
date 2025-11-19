package com.heysmile.heysmileapi.dtos.reminder.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateReminderRequestDto {

    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime date;
}