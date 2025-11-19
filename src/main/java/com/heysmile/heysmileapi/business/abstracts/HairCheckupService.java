package com.heysmile.heysmileapi.business.abstracts;

import com.heysmile.heysmileapi.dtos.haircheckup.response.HairCheckupResponseDto;
import com.heysmile.heysmileapi.entities.HairCheckup;
import com.heysmile.heysmileapi.entities.User;
import java.util.List;

public interface HairCheckupService {
    HairCheckup createCheckup(HairCheckup checkup);
    List<HairCheckupResponseDto> getUserCheckups(User user);
}