package com.heysmile.heysmileapi.dtos.treatment.response;

import lombok.Data;
import java.util.UUID;

@Data
public class TreatmentResponseDto {
    private UUID id;
    private String title;
    private String description;
    private String pageUrl;
}