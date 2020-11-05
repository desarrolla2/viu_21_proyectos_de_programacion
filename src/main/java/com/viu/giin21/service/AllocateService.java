package com.viu.giin21.service;

import com.viu.giin21.dto.JobDTO;

/**
 * This service handle the priorities and other requisites for jobs to workplace assignation
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
public interface AllocateService {

    /**
     * Try to allocate Job in some workplace
     *
     * @param job to be allocated
     * @return it allocation was successfully
     */
    boolean allocate(JobDTO job);
}
