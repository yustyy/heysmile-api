package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.dtos.treatment.response.TreatmentResponseDto;
import com.heysmile.heysmileapi.entities.Treatment;
import java.util.List;
import java.util.UUID;

public interface TreatmentService {
    Treatment createTreatment(Treatment treatment);
    TreatmentResponseDto getTreatmentById(UUID id);
    List<TreatmentResponseDto> getAllTreatments();
}