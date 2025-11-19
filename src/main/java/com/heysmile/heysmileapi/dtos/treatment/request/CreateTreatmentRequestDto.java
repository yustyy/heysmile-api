package com.heysmile.heysmileapi.dtos.treatment.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateTreatmentRequestDto {
    @NotNull
    private String title;
    private String description;
    private String pageUrl;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private UUID userId;
}