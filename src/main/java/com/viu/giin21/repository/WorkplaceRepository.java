package com.viu.giin21.repository;

import com.viu.giin21.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository handle communication with database for entity Workplace
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Integer> {
}
