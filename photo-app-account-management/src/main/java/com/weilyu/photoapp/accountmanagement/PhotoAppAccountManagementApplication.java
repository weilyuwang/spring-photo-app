package com.weilyu.photoapp.accountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppAccountManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppAccountManagementApplication.class, args);
    }

}
