package com.weilyu.photoapp.photoappusersservice.ui.controllers;


import com.weilyu.photoapp.photoappusersservice.service.UsersService;
import com.weilyu.photoapp.photoappusersservice.shared.UserDto;
import com.weilyu.photoapp.photoappusersservice.ui.model.CreateUserRequestModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment env;

    private final UsersService usersService;

    public UsersController(Environment env, UsersService usersService) {
        this.env = env;
        this.usersService = usersService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + env.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {   // convert JSON payload into CreateUserRequestModel object

        // user ModelMapper to map POST request body model into DTO object
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        usersService.createUser(userDto);

        return "createUser method is called.";
    }

}
