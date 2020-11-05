package com.viu.giin21.repository;

import com.viu.giin21.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository handle communication with database for entity User
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByUsername(String username);
}
