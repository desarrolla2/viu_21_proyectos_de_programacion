package com.viu.giin21.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * This entity represent a user of application
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Entity
@Table(name = "user")
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String role;

    /**
     * No arguments constructor
     */
    public User() {
    }
}
