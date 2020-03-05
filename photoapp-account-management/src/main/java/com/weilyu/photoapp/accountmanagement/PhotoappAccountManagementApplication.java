package com.weilyu.photoapp.accountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoappAccountManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoappAccountManagementApplication.class, args);
    }

}
