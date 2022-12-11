package com.networks.tea.service.service_impl;

import com.networks.tea.enums.ERole;
import com.networks.tea.model.Role;
import com.networks.tea.repository.RoleRepository;
import com.networks.tea.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class RoleService implements IRoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(ERole name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }
}
