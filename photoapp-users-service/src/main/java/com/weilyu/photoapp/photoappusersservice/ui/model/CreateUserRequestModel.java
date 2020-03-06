package com.weilyu.photoapp.photoappusersservice.ui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequestModel {

    @NotNull(message = "First name can not be null")
    @Size(min = 2, message = "First name must not be less than 2 characters")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    @Size(min = 2, message = "Last name must not be less than 2 characters")
    private String lastName;

    @NotNull(message = "Password can not be null")
    @Size(min = 8, max = 16, message = "Password must be equal or greater than 8 characters and less than 16 characters")
    private String password;

    @NotNull(message = "Email can not be null")
    @Email(message = "Please provide a valid email")
    private String email;

}
