package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.dtos.haircheckup.response.HairCheckupResponseDto;
import com.heysmile.heysmileapi.entities.HairCheckup;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HairCheckupService {
    HairCheckupResponseDto createCheckup(MultipartFile imageFront,
                                         MultipartFile imageBack,
                                         MultipartFile imageLeft,
                                         MultipartFile imageRight,
                                         MultipartFile imageTop,
                                         String userNotes);



    List<HairCheckupResponseDto> getAuthenticatedUserCheckups();
}