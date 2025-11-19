package com.heysmile.heysmileapi.dtos.calendar.request;

import com.heysmile.heysmileapi.entities.CalendarCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCalendarRequestDto {

    @NotNull
    private CalendarCategory category;

    @NotNull
    private LocalDateTime date;

}