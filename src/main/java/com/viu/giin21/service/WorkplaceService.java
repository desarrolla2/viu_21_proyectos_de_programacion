package com.viu.giin21.service;

import com.viu.giin21.dto.WorkplaceDTO;

import java.util.List;

/**
 * This service handle some queries from database and return JobDTO instead of Workplace entity
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
public interface WorkplaceService {
    /**
     * find all available workplaces
     *
     * @return a list of DTOs
     */
    List<WorkplaceDTO> findAll();

    /**
     * @param workplace to be persisted
     * @return primary key
     */
    Integer save(WorkplaceDTO workplace);

    /**
     * find workplace by primary key
     *
     * @param id primary key
     * @return the workplace
     */
    WorkplaceDTO get(Integer id);

    /**
     * delete workplace by primary key
     *
     * @param id primary key
     */
    void delete(Integer id);

    /**
     * Count workplace in database
     *
     * @return number of jobs
     */
    long count();
}

