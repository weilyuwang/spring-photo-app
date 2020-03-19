package com.weilyu.photoapp.photoappusersservice;

import com.weilyu.photoapp.photoappusersservice.shared.FeignErrorDecoder;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PhotoappUsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoappUsersServiceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @LoadBalanced //enable client-side load balancing
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    //Feign Logger Level Bean
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    //Feign Error Decoder Bean
    @Bean
    public FeignErrorDecoder getFeignErrorDecoder() {
        return new FeignErrorDecoder();
    }
}
