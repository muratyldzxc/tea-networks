package com.networks.tea.service;

import com.networks.tea.enums.ERole;
import com.networks.tea.model.Role;

import java.util.Optional;

public interface IRoleService {
    Role findByName(ERole name);
}
