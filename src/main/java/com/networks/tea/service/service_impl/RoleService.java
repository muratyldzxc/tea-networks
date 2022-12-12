package com.networks.tea.service.service_impl;

import com.networks.tea.enums.ERole;
import com.networks.tea.model.Role;
import com.networks.tea.service.IRoleService;
import com.networks.tea.repository.RoleRepository;
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

    @Override
    public Role add(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public Role update(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
