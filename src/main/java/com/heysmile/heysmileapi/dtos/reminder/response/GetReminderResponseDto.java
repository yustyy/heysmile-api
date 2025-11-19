package com.heysmile.heysmileapi.dtos.reminder.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetReminderResponseDto {

    private UUID id;

    private String title;

    private String content;

    private LocalDate date;


}
