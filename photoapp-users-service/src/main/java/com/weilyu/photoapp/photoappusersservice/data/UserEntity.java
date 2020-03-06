package com.weilyu.photoapp.photoappusersservice.data;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue //The GenerationType.AUTO is the default generation type and lets the persistence provider choose the generation strategy
    private long id;

    // public user ID (generated from UUID)
    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    // We don't want to store plain text password in our database, only encrypted password should be stored
//    private String password;

    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String encryptedPassword;

}
