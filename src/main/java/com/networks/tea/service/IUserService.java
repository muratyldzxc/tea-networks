package com.networks.tea.service;

import com.networks.tea.model.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserService extends ICrudService<UUID, User>{
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
