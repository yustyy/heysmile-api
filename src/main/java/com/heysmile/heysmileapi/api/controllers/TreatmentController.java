package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.TreatmentService;
import com.heysmile.heysmileapi.core.utilities.results.DataResult;
import com.heysmile.heysmileapi.core.utilities.results.Result;
import com.heysmile.heysmileapi.core.utilities.results.SuccessDataResult;
import com.heysmile.heysmileapi.core.utilities.results.SuccessResult;
import com.heysmile.heysmileapi.dtos.treatment.response.TreatmentResponseDto;
import com.heysmile.heysmileapi.entities.Treatment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<TreatmentResponseDto>>> getAll() {
        var result = treatmentService.getAllTreatments();
        return ResponseEntity.ok(new SuccessDataResult<>(result, "Treatments listed", HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<TreatmentResponseDto>> getById(@PathVariable UUID id) {
        var result = treatmentService.getTreatmentById(id);
        return ResponseEntity.ok(new SuccessDataResult<>(result, "Treatment found", HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<Result> create(@RequestBody Treatment treatment) {
        treatmentService.createTreatment(treatment);
        return ResponseEntity.ok(new SuccessResult("Treatment created", HttpStatus.CREATED));
    }
}