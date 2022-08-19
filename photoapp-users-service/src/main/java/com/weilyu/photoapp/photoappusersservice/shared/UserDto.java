package com.weilyu.photoapp.photoappusersservice.shared;


import com.weilyu.photoapp.photoappusersservice.ui.model.AlbumResponseModel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;

}
