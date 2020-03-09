package com.weilyu.photoapp.photoappusersservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weilyu.photoapp.photoappusersservice.service.UsersService;
import com.weilyu.photoapp.photoappusersservice.shared.UserDto;
import com.weilyu.photoapp.photoappusersservice.ui.model.LoginRequestModel;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UsersService usersService;

    private final Environment environment;

    public AuthenticationFilter(UsersService usersService, Environment environment, AuthenticationManager authenticationManager) {
        this.usersService = usersService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // get loginRequestModel that user provides from user input that contains user's credentials
            LoginRequestModel credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequestModel.class);


            // generate an auth token for the login user
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>()
                    )
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // this method will be called by Spring if user auth is successful ( after attemptAuthentication() )
    // job of this method will be generating JWT token based on the login user,
    // then add the JWT token to the response header and return it to the login user with the http response
    // user/client then will be able to use the given JWT token in the subsequent requests (include it in the http request auth header)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        // get the username (user email) from the authResult
        String username = ((User) authResult.getPrincipal()).getUsername(); // the username is actually the user-provided email address
        // from username, find the user detail (we will use the public user id to generate json web token for the login user)
        UserDto userDetails = usersService.getUserDetailsByEmail(username);

        // generate a JWT token for the login user
        String token = Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))  //Base64-encoded key bytes may only be specified for HMAC signatures
                .compact();

        // add JWT token to response http header
        response.addHeader("token", token);
        // add public user id to response http header
        response.addHeader("userId", userDetails.getUserId());
    }
}
