package com.viu.giin21.service;

import com.viu.giin21.dto.JobDTO;

import java.util.List;

/**
 * This service handle some queries from database and return JobDTO instead of Job entity
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
public interface JobService {

    /**
     * find all available jobs
     *
     * @return a list of DTOs
     */
    List<JobDTO> findAll();

    /**
     * @param job to be persisted
     * @return primary key
     */
    Integer save(JobDTO job);

    /**
     * find Job by primary key
     *
     * @param id primary key
     * @return the job
     */
    JobDTO get(Integer id);

    /**
     * delete job by primary key
     *
     * @param id primary key
     */
    void delete(Integer id);

    /**
     * Count jobs in database
     *
     * @return number of jobs
     */
    long count();

    /**
     * Find jobs by next arguments
     *
     * @param workplace assigned workplace
     * @param finalized is finalized
     * @return a list of DTOs
     */
    List<JobDTO> findByWorkplaceAndFinalized(Integer workplace, Boolean finalized);

    /**
     * Find jobs by next arguments
     *
     * @param finalized is finalized
     * @return a list of DTOs
     */
    List<JobDTO> findByFinalized(Boolean finalized);

    /**
     * @param started is started
     * @return a list of DTOs
     */
    List<JobDTO> findByStarted(Boolean started);
}
