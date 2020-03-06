package com.weilyu.photoapp.photoappusersservice.ui.controllers;


import com.weilyu.photoapp.photoappusersservice.ui.model.CreateUserRequestModel;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment env;

    public UsersController(Environment env) {
        this.env = env;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + env.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {   // convert JSON payload into CreateUserRequestModel object
        return "createUser method is called.";
    }

}
