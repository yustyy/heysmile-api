package com.heysmile.heysmileapi.dtos.calendar.response;

import com.heysmile.heysmileapi.entities.CalendarCategory;
import com.heysmile.heysmileapi.dtos.treatment.response.TreatmentResponseDto;
import com.heysmile.heysmileapi.dtos.haircheckup.response.HairCheckupResponseDto;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CalendarResponseDto {
    private UUID id;
    private LocalDateTime date;
    private CalendarCategory category;

    private TreatmentResponseDto treatment;
    private HairCheckupResponseDto hairCheckup;
}