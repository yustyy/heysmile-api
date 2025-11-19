package com.heysmile.heysmileapi.business.concretes;

import com.heysmile.heysmileapi.business.abstracts.CalendarService;
import com.heysmile.heysmileapi.business.abstracts.HairCheckupService;
import com.heysmile.heysmileapi.business.abstracts.ImageService;
import com.heysmile.heysmileapi.business.abstracts.UserService;
import com.heysmile.heysmileapi.dataAccess.HairCheckupDao;
import com.heysmile.heysmileapi.dtos.haircheckup.response.HairCheckupResponseDto;
import com.heysmile.heysmileapi.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class HairCheckupManager implements HairCheckupService {

    private final HairCheckupDao hairCheckupDao;
    private final ImageService imageService;
    private final String BASE_IMAGE_URL = "https://hey-smile-api.yusufacmaci.com/api/images/";
    private final UserService userService;
    private final CalendarService calendarService;

    public HairCheckupManager(HairCheckupDao hairCheckupDao, ImageService imageService, UserService userService, CalendarService calendarService){
        this.hairCheckupDao = hairCheckupDao;
        this.imageService = imageService;
        this.userService = userService;
        this.calendarService = calendarService;
    }


    @Override
    @Transactional
    public HairCheckupResponseDto createCheckup(MultipartFile imageFront, MultipartFile imageBack, MultipartFile imageLeft, MultipartFile imageRight, MultipartFile imageTop, String userNotes) {

        if (imageFront == null || imageBack == null || imageLeft == null || imageRight == null || imageTop == null) {
            throw new IllegalArgumentException("All images must be provided.");
        }

        var userReference = userService.getAuthenticatedUserReference();

        Image frontImage = imageService.uploadImage(imageFront);

        Image backImage = imageService.uploadImage(imageBack);

        Image leftImage = imageService.uploadImage(imageLeft);

        Image rightImage = imageService.uploadImage(imageRight);

        Image topImage = imageService.uploadImage(imageTop);



        HairCheckup hairCheckup = new HairCheckup();
        hairCheckup.setImageFront(frontImage);
        hairCheckup.setImageBack(backImage);
        hairCheckup.setImageLeft(leftImage);
        hairCheckup.setImageRight(rightImage);
        hairCheckup.setImageTop(topImage);
        hairCheckup.setUserNotes(userNotes);
        hairCheckup.setUser(userReference);

        Calendar calendar = new Calendar();
        calendar.setDate(LocalDate.now());
        calendar.setUser(userReference);
        calendar.setCategory(CalendarCategory.HAIR_CHECKUP);

        calendar.setHairCheckup(hairCheckup);
        hairCheckup.setCalendar(calendar);

        calendarService.createCalendar(calendar);

        var result = hairCheckupDao.save(hairCheckup);

        HairCheckupResponseDto responseDto = new HairCheckupResponseDto();
        responseDto.setId(result.getId());
        responseDto.setDate(result.getCalendar().getDate());
        responseDto.setUserNotes(result.getUserNotes());
        responseDto.setDoctorComment(result.getDoctorComment());
        responseDto.setGraft(result.getGraft());
        responseDto.setImageFrontUrl(BASE_IMAGE_URL + result.getImageFront().getUrl());
        responseDto.setImageBackUrl(BASE_IMAGE_URL + result.getImageBack().getUrl());
        responseDto.setImageLeftUrl(BASE_IMAGE_URL + result.getImageLeft().getUrl());
        responseDto.setImageRightUrl(BASE_IMAGE_URL + result.getImageRight().getUrl());
        responseDto.setImageTopUrl(BASE_IMAGE_URL + result.getImageTop().getUrl());
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HairCheckupResponseDto> getAuthenticatedUserCheckups() {
        var userReference = userService.getAuthenticatedUserReference();
        var checkups = hairCheckupDao.findAllByUser(userReference);

        return checkups.stream().map(result -> {
            HairCheckupResponseDto responseDto = new HairCheckupResponseDto();
            responseDto.setId(result.getId());
            responseDto.setDate(result.getCalendar().getDate());
            responseDto.setUserNotes(result.getUserNotes());
            responseDto.setDoctorComment(result.getDoctorComment());
            responseDto.setGraft(result.getGraft());
            responseDto.setImageFrontUrl(BASE_IMAGE_URL + result.getImageFront().getUrl());
            responseDto.setImageBackUrl(BASE_IMAGE_URL + result.getImageBack().getUrl());
            responseDto.setImageLeftUrl(BASE_IMAGE_URL + result.getImageLeft().getUrl());
            responseDto.setImageRightUrl(BASE_IMAGE_URL + result.getImageRight().getUrl());
            responseDto.setImageTopUrl(BASE_IMAGE_URL + result.getImageTop().getUrl());
            return responseDto;
        }).toList();
    }
}