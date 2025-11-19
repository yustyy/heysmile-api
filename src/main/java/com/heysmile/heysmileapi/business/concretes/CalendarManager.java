package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.CalendarService;
import com.heysmile.heysmileapi.dataAccess.CalendarDao;
import com.heysmile.heysmileapi.dtos.calendar.response.CalendarResponseDto;
import com.heysmile.heysmileapi.dtos.haircheckup.response.HairCheckupResponseDto;
import com.heysmile.heysmileapi.dtos.treatment.response.TreatmentResponseDto;
import com.heysmile.heysmileapi.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CalendarManager implements CalendarService {

    private final CalendarDao calendarDao;
    private final String BASE_IMAGE_URL = "https://hey-smile-api.yusufacmaci.com/api/images/";


    public CalendarManager(CalendarDao calendarDao) {
        this.calendarDao = calendarDao;
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        return calendarDao.save(calendar);
    }

    @Override
    public List<CalendarResponseDto> getUserCalendars(User user) {
        return List.of();
    }


    @Override
    @Transactional
    public void deleteCalendar(UUID id) {
        calendarDao.deleteById(id);
    }



    private TreatmentResponseDto mapTreatment(Treatment treatment) {
        TreatmentResponseDto dto = new TreatmentResponseDto();
        dto.setId(treatment.getId());
        dto.setTitle(treatment.getTitle());
        dto.setDescription(treatment.getDescription());
        dto.setPageUrl(treatment.getPageUrl());
        return dto;
    }


    private HairCheckupResponseDto mapHairCheckup(HairCheckup checkup) {
        HairCheckupResponseDto dto = new HairCheckupResponseDto();
        dto.setId(checkup.getId());
        dto.setUserNotes(checkup.getUserNotes());
        dto.setDoctorComment(checkup.getDoctorComment());
        dto.setGraft(checkup.getGraft());

        if (checkup.getImageFront() != null) dto.setImageFrontUrl(BASE_IMAGE_URL + checkup.getImageFront().getUrl());
        if (checkup.getImageBack() != null) dto.setImageBackUrl(BASE_IMAGE_URL + checkup.getImageBack().getUrl());
        if (checkup.getImageLeft() != null) dto.setImageLeftUrl(BASE_IMAGE_URL + checkup.getImageLeft().getUrl());
        if (checkup.getImageRight() != null) dto.setImageRightUrl(BASE_IMAGE_URL + checkup.getImageRight().getUrl());
        if (checkup.getImageTop() != null) dto.setImageTopUrl(BASE_IMAGE_URL + checkup.getImageTop().getUrl());

        return dto;
    }
}