package com.viu.giin21.repository;

import com.viu.giin21.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This repository handle communication with database for entity Job
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    /**
     * Widh Jobs by Workplace and Finalized
     *
     * @author Daniel González daniel@devtia.com
     * @version 0.1.0
     */
    List<Job> findByWorkplaceAndFinalized(Integer workplace, Boolean finalized);


    /**
     * @param workplace
     * @param started
     * @return
     */
    List<Job> findByWorkplaceAndStarted(Integer workplace, Boolean started);

    /**
     * @param finalized
     * @return
     */
    List<Job> findByFinalized(Boolean finalized);

    /**
     * @param started
     * @return
     */
    List<Job> findByStarted(Boolean started);
}
