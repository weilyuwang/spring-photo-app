package com.weilyu.photoapp.photoappusersservice.service;


import com.weilyu.photoapp.photoappusersservice.data.AlbumsServiceFeignClient;
import com.weilyu.photoapp.photoappusersservice.data.UserEntity;
import com.weilyu.photoapp.photoappusersservice.data.UserRepository;
import com.weilyu.photoapp.photoappusersservice.shared.UserDto;
import com.weilyu.photoapp.photoappusersservice.ui.model.AlbumResponseModel;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService{

    private final UserRepository userRepository;

    // to get encoder injected into UsersServiceImpl, need to create a bean of this encoder
    private final BCryptPasswordEncoder passwordEncoder;

    // Feign client for making HTTP requests
    private final AlbumsServiceFeignClient albumsServiceClient;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public UsersServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AlbumsServiceFeignClient albumsServiceClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.albumsServiceClient = albumsServiceClient;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {

        // set an unique user ID (UUID)
        userDetails.setUserId(UUID.randomUUID().toString());
        // encrypt user password before save user into database
        userDetails.setEncryptedPassword(passwordEncoder.encode(userDetails.getPassword()));

        // user ModelMapper to map DTO into JPA Entity ( field variable names in DTO and Entity must match (be the same) )
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        // save entity into database using repository
        userRepository.save(userEntity);

        // return the saved userDto
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserDetailsByEmail(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if(userEntity == null) throw new UsernameNotFoundException(username);

        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if(userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        // use Feign Client to send HTTP get request to albums-service to get user's albums data:
        List<AlbumResponseModel> albumsList = null;

        try {
            albumsList = albumsServiceClient.getAlbums(userId);
        } catch (FeignException e) {
            // handle Feign Exception
            logger.error(e.getLocalizedMessage());
        }

        userDto.setAlbums(albumsList);

        return userDto;
    }
}
