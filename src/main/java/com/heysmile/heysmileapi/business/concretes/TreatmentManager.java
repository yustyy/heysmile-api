package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.TreatmentService;
import com.heysmile.heysmileapi.core.exceptions.NotFoundException;
import com.heysmile.heysmileapi.dataAccess.TreatmentDao;
import com.heysmile.heysmileapi.dtos.treatment.response.TreatmentResponseDto;
import com.heysmile.heysmileapi.entities.Treatment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TreatmentManager implements TreatmentService {

    private final TreatmentDao treatmentDao;


    public TreatmentManager(TreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }

    @Override
    public Treatment createTreatment(Treatment treatment) {
        return  treatmentDao.save(treatment);
    }

    @Override
    public TreatmentResponseDto getTreatmentById(UUID id) {
        Treatment treatment = treatmentDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Treatment not found"));
        return mapToDto(treatment);
    }

    @Override
    public List<TreatmentResponseDto> getAllTreatments() {
        return treatmentDao.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TreatmentResponseDto mapToDto(Treatment treatment) {
        TreatmentResponseDto dto = new TreatmentResponseDto();
        dto.setId(treatment.getId());
        dto.setTitle(treatment.getTitle());
        dto.setDescription(treatment.getDescription());
        dto.setPageUrl(treatment.getPageUrl());
        return dto;
    }
}