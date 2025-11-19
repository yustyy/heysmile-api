package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.HairCheckupService;
import com.heysmile.heysmileapi.dataAccess.HairCheckupDao;
import com.heysmile.heysmileapi.dtos.haircheckup.response.HairCheckupResponseDto;
import com.heysmile.heysmileapi.entities.HairCheckup;
import com.heysmile.heysmileapi.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HairCheckupManager implements HairCheckupService {

    private final HairCheckupDao hairCheckupDao;
    private final String BASE_IMAGE_URL = "https://hey-smile-api.yusufacmaci.com/api/images/";

    public HairCheckupManager(HairCheckupDao hairCheckupDao) {
        this.hairCheckupDao = hairCheckupDao;
    }

    @Override
    public HairCheckup createCheckup(HairCheckup checkup) {
        return hairCheckupDao.save(checkup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HairCheckupResponseDto> getUserCheckups(User user) {
        return hairCheckupDao.findAllByUser(user).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private HairCheckupResponseDto mapToDto(HairCheckup entity) {
        HairCheckupResponseDto dto = new HairCheckupResponseDto();
        dto.setId(entity.getId());
        dto.setUserNotes(entity.getUserNotes());
        dto.setDoctorComment(entity.getDoctorComment());
        dto.setGraft(entity.getGraft());

        if (entity.getImageFront() != null) dto.setImageFrontUrl(BASE_IMAGE_URL + entity.getImageFront().getUrl());
        if (entity.getImageBack() != null) dto.setImageBackUrl(BASE_IMAGE_URL + entity.getImageBack().getUrl());
        if (entity.getImageLeft() != null) dto.setImageLeftUrl(BASE_IMAGE_URL + entity.getImageLeft().getUrl());
        if (entity.getImageRight() != null) dto.setImageRightUrl(BASE_IMAGE_URL + entity.getImageRight().getUrl());
        if (entity.getImageTop() != null) dto.setImageTopUrl(BASE_IMAGE_URL + entity.getImageTop().getUrl());

        return dto;
    }
}