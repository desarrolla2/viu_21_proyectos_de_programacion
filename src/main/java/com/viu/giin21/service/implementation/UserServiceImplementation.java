package com.viu.giin21.service.implementation;

import com.viu.giin21.dto.UserDTO;
import com.viu.giin21.model.User;
import com.viu.giin21.repository.UserRepository;
import com.viu.giin21.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service handle some queries from database and return JobDTO instead of User entity
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;

    /**
     * Constructor
     *
     * @param repository user repository
     */
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer save(UserDTO user) {
        User entity = new User(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
        repository.save(entity);
        return entity.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO get(Integer id) {
        User entity = repository.getOne(id);
        return toDto(entity);
    }

    private UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getRole());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return repository.count();
    }
}


