package com.weilyu.photoapp.photoappusersservice.ui.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponseModel {

    private String firstName;

    private String lastName;

    private String email;

    private String userId;  // public visible unique user ID

}
