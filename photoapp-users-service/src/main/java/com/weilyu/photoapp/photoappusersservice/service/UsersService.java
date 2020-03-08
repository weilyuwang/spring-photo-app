package com.weilyu.photoapp.photoappusersservice.service;

import com.weilyu.photoapp.photoappusersservice.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

    UserDto createUser(UserDto userDetails);

    UserDto getUserDetailsByEmail(String email);

}
