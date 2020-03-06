package com.weilyu.photoapp.photoappusersservice.shared;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String firstName;
    private String lastName;
    private String password;
    private String email;

    private String encryptedPassword;
}
