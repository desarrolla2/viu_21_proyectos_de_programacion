package com.viu.giin21.service.implementation;

import com.viu.giin21.dto.WorkplaceDTO;
import com.viu.giin21.model.User;
import com.viu.giin21.model.Workplace;
import com.viu.giin21.repository.UserRepository;
import com.viu.giin21.repository.WorkplaceRepository;
import com.viu.giin21.service.WorkplaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service handle some queries from database and return JobDTO instead of Workplace entity
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Service
public class WorkplaceServiceImplementation implements WorkplaceService {

    private final WorkplaceRepository workplaceRepository;

    private final UserRepository userRepository;

    /**
     * Constructor
     *
     * @param userRepository      user repository
     * @param workplaceRepository workplace repository
     */
    public WorkplaceServiceImplementation(UserRepository userRepository, WorkplaceRepository workplaceRepository) {
        this.userRepository = userRepository;
        this.workplaceRepository = workplaceRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WorkplaceDTO> findAll() {
        return workplaceRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer save(WorkplaceDTO workplace) {
        Workplace entity = new Workplace(workplace.getId(), workplace.getOwner(), workplace.getName(), workplace.getProcessability(), workplace.getMaximumQueueSize());
        workplaceRepository.save(entity);
        return entity.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkplaceDTO get(Integer id) {
        Workplace entity = workplaceRepository.getOne(id);
        return toDto(entity);
    }

    private WorkplaceDTO toDto(Workplace entity) {
        WorkplaceDTO workplace = new WorkplaceDTO(entity.getId(), entity.getOwner(), entity.getName(), entity.getProcessability(), entity.getMaximumQueueSize());
        toDtoSetUsername(entity, workplace);
        return workplace;
    }

    private void toDtoSetUsername(Workplace entity, WorkplaceDTO dto) {
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
        workplaceRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return workplaceRepository.count();
    }
}


