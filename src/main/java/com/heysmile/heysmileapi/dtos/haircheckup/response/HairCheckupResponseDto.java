package com.heysmile.heysmileapi.dtos.haircheckup.response;

import lombok.Data;
import java.util.UUID;

@Data
public class HairCheckupResponseDto {
    private UUID id;
    private String userNotes;
    private String doctorComment;
    private int graft;

    private String imageFrontUrl;
    private String imageBackUrl;
    private String imageLeftUrl;
    private String imageRightUrl;
    private String imageTopUrl;
}