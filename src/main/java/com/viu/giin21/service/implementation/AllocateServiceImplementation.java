package com.viu.giin21.service.implementation;

import com.viu.giin21.dto.JobDTO;
import com.viu.giin21.model.Job;
import com.viu.giin21.model.Workplace;
import com.viu.giin21.repository.JobRepository;
import com.viu.giin21.repository.WorkplaceRepository;
import com.viu.giin21.service.AllocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This service handle the priorities and other requisites for jobs to workplace assignation
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Service
public class AllocateServiceImplementation implements AllocateService {

    @Autowired
    private WorkplaceRepository workplaceRepository;

    @Autowired
    private JobRepository jobRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean allocate(JobDTO job) {
        if (job.getWorkplace() != null) {
            return false;
        }
        List<Workplace> workplaces = workplaceRepository.findAll();
        Collections.sort(workplaces, new Comparator<Workplace>() {
            @Override
            public int compare(Workplace workplace1, Workplace workplace2) {
                float estimatedTime1 = getEstimatedTime(workplace1);
                float estimatedTime2 = getEstimatedTime(workplace2);
                if (estimatedTime1 == estimatedTime2) {
                    return 0;
                }

                //return estimatedTime1 >= estimatedTime2 ? 1 : -1;
                if (Double.compare(estimatedTime1, estimatedTime2) >= 0) {
                    return 1;
                }
                return -1;
            }
        });

        for (Workplace workplace : workplaces) {
            List<Job> jobs = jobRepository.findByWorkplaceAndFinalized(workplace.getId(), false);
            if (jobs.stream().count() >= workplace.getMaximumQueueSize()) {
                continue;
            }
            Job entity = jobRepository.getOne(job.getId());
            entity.setWorkplace(workplace.getId());
            jobRepository.save(entity);

            return true;
        }

        return false;
    }

    private float getEstimatedTime(Workplace workplace) {
        int pendingOperations = 0;

        List<Job> jobs = jobRepository.findByWorkplaceAndFinalized(workplace.getId(), false);
        for (Job job : jobs) {
            pendingOperations = pendingOperations + job.getSize() - job.getExecuted();
        }
        return (float) pendingOperations / workplace.getProcessability() + (jobs.stream().count() + 1) * 10;
    }
}
