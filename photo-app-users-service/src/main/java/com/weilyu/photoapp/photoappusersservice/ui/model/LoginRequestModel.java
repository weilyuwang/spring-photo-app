package com.weilyu.photoapp.photoappusersservice.ui.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestModel {

    private String password;
    private String email;

}
