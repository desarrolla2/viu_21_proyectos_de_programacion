package com.viu.giin21.service.implementation;

import com.viu.giin21.dto.JobDTO;
import com.viu.giin21.model.Job;
import com.viu.giin21.model.User;
import com.viu.giin21.model.Workplace;
import com.viu.giin21.repository.JobRepository;
import com.viu.giin21.repository.UserRepository;
import com.viu.giin21.repository.WorkplaceRepository;
import com.viu.giin21.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service handle some queries from database and return JobDTO instead of Job entity
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Service
public class JobServiceImplementation implements JobService {

    private final UserRepository userRepository;
    private final WorkplaceRepository workplaceRepository;
    private final JobRepository jobRepository;

    /**
     * Constructor
     *
     * @param userRepository      user repository
     * @param workplaceRepository workplace repository
     * @param jobRepository       job repository
     */
    public JobServiceImplementation(UserRepository userRepository, WorkplaceRepository workplaceRepository, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.workplaceRepository = workplaceRepository;
        this.jobRepository = jobRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobDTO> findAll() {
        return jobRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer save(JobDTO job) {
        Job entity = new Job(job.getId(), job.getOwner(), job.getWorkplace(), job.getName(), job.getSize());
        entity.setExecuted(job.getExecuted());
        jobRepository.save(entity);
        return entity.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobDTO get(Integer id) {
        Job entity = jobRepository.getOne(id);
        return toDto(entity);
    }

    private JobDTO toDto(Job entity) {
        JobDTO jobDTO = new JobDTO(entity.getId(), entity.getOwner(), entity.getWorkplace(), entity.getName(), entity.getExecuted(), entity.getSize(), entity.getStarted(), entity.getFinalized());
        toDtoSetUsername(entity, jobDTO);
        toDtoSetWorkplaceName(entity, jobDTO);
        return jobDTO;
    }

    private void toDtoSetWorkplaceName(Job entity, JobDTO dto) {
        if (entity.getWorkplace() == null) {
            return;
        }
        Workplace workplace = workplaceRepository.getOne(entity.getWorkplace());
        if (workplace == null) {
            return;
        }
        dto.setWorkplaceName(workplace.getName());
    }

    private void toDtoSetUsername(Job entity, JobDTO dto) {
        if (entity.getOwner() == null) {
            return;
        }
        User user = userRepository.getOne(entity.getOwner());
        if (user == null) {
            return;
        }
        dto.setOwnerUsername(user.getUsername());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id) {
        jobRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return jobRepository.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobDTO> findByWorkplaceAndFinalized(Integer workplace, Boolean finalized) {
        return jobRepository
                .findByWorkplaceAndFinalized(workplace, finalized)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobDTO> findByFinalized(Boolean finalized) {
        return jobRepository
                .findByFinalized(finalized)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobDTO> findByStarted(Boolean started) {
        return jobRepository
                .findByStarted(started)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
