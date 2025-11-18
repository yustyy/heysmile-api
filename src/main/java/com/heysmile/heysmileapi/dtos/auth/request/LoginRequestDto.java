package com.heysmile.heysmileapi.dtos.auth.request;

import com.heysmile.heysmileapi.core.constants.AuthMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotNull(message = AuthMessages.EMAIL_NOT_NULL)
    @Email(message = AuthMessages.EMAIL_INVALID)
    private String email;

    @NotNull(message = AuthMessages.PASSWORD_NOT_NULL)
    private String password;

}
