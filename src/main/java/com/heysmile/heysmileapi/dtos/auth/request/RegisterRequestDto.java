package com.heysmile.heysmileapi.dtos.auth.request;

import com.heysmile.heysmileapi.core.constants.AuthMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotNull(message = AuthMessages.FIRST_NAME_NOT_NULL)
    private String firstName;

    @NotNull(message = AuthMessages.LAST_NAME_NOT_NULL)
    private String lastName;

    @NotNull(message = AuthMessages.DATE_OF_BIRTH_NOT_NULL)
    private LocalDate dateOfBirth;

    @NotNull(message = AuthMessages.EMAIL_NOT_NULL)
    @Email(message = AuthMessages.EMAIL_INVALID)
    private String email;

    @NotNull(message = AuthMessages.PHONE_NUMBER_NOT_NULL)
    private String phoneNumber;

    @NotNull(message = AuthMessages.PASSWORD_NOT_NULL)
    private String password;


}
