package com.weilyu.photoapp.photoappusersservice.service;


import com.weilyu.photoapp.photoappusersservice.data.UserEntity;
import com.weilyu.photoapp.photoappusersservice.data.UserRepository;
import com.weilyu.photoapp.photoappusersservice.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;   // to get encoder injected into UsersServiceImpl, need to create a bean of this encoder

    public UsersServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {

        // set an unique user ID (UUID)
        userDetails.setUserId(UUID.randomUUID().toString());

        // user ModelMapper to map DTO into JPA Entity ( field variable names in DTO and Entity must match (be the same) )
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        // encrypt user password before persistence into database
        // for now, hard code it. TODO encrypt user password
        userEntity.setEncryptedPassword("test_encrypt_pass");

        // save entity into database using repository
        userRepository.save(userEntity);

        // return the saved userDto
        return modelMapper.map(userEntity, UserDto.class);
    }
}
