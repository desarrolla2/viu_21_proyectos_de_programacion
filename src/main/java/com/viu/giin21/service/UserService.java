package com.viu.giin21.service;

import com.viu.giin21.dto.UserDTO;

import java.util.List;

/**
 * This service handle some queries from database and return JobDTO instead of User entity
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
public interface UserService {
    /**
     * find all available users
     *
     * @return a list of DTOs
     */
    List<UserDTO> findAll();

    /**
     * @param user to be persisted
     * @return primary key
     */
    Integer save(UserDTO user);

    /**
     * find user by primary key
     *
     * @param id primary key
     * @return the user
     */
    UserDTO get(Integer id);

    /**
     * delete user by primary key
     *
     * @param id primary key
     */
    void delete(Integer id);

    /**
     * Count users in database
     *
     * @return number of users
     */
    long count();
}
