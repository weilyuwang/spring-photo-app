package com.weilyu.photoapp.photoappalbumsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppAlbumsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppAlbumsServiceApplication.class, args);
    }

}
