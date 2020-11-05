package com.viu.giin21.service.implementation;

import com.viu.giin21.model.Job;
import com.viu.giin21.model.Workplace;
import com.viu.giin21.repository.JobRepository;
import com.viu.giin21.repository.WorkplaceRepository;
import com.viu.giin21.service.SimulateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service handle the simulation of time
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Service
public class SimulateServiceImplementation implements SimulateService {

    private final WorkplaceRepository workplaceRepository;

    private final JobRepository jobRepository;

    /**
     * Constructor
     *
     * @param workplaceRepository workplace repository
     * @param jobRepository       job repository
     */
    public SimulateServiceImplementation(WorkplaceRepository workplaceRepository, JobRepository jobRepository) {
        this.workplaceRepository = workplaceRepository;
        this.jobRepository = jobRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean simulate(Integer seconds) {
        List<Workplace> workplaces = workplaceRepository.findAll();
        for (Workplace workplace : workplaces) {
            for (int i = 1; i <= seconds; i++) {
                Job current = getOneStartedAndNotFinalized(workplace);
                if (current == null) {
                    load(workplace);
                    continue;
                }
                if (current.getExecuted() < current.getSize()) {
                    execute(workplace, current);
                    continue;
                }
                unload(workplace, current);
            }
        }
        return false;
    }

    private void execute(Workplace workplace, Job job) {
        Integer executed = job.getExecuted() + workplace.getProcessability();
        job.setExecuted(executed);
        jobRepository.save(job);
    }

    private void unload(Workplace workplace, Job job) {
        if (workplace.getElapsedUnloadTime() < 5) {
            workplace.setElapsedUnloadTime(workplace.getElapsedUnloadTime() + 1);
            workplaceRepository.save(workplace);
            return;
        }
        workplace.setElapsedLoadTime(0);
        workplace.setElapsedUnloadTime(0);
        job.setFinalized(true);

        jobRepository.save(job);
        workplaceRepository.save(workplace);
    }

    private void load(Workplace workplace) {
        Job target = getOneNotStarted(workplace);
        if (target == null) {
            return;
        }

        if (workplace.getElapsedLoadTime() < 4) {
            workplace.setElapsedLoadTime(workplace.getElapsedLoadTime() + 1);
            workplaceRepository.save(workplace);
            return;
        }
        target.setStarted(true);
        jobRepository.save(target);

    }

    private Job getOneNotStarted(Workplace workplace) {
        List<Job> jobs = jobRepository.findByWorkplaceAndStarted(workplace.getId(), false);
        for (Job job : jobs) {
            return job;
        }
        return null;
    }

    private Job getOneStartedAndNotFinalized(Workplace workplace) {
        List<Job> jobs = jobRepository.findByWorkplaceAndStarted(workplace.getId(), true);
        for (Job job : jobs) {
            if (job.getFinalized()) {
                continue;
            }
            return job;
        }
        return null;
    }
}
