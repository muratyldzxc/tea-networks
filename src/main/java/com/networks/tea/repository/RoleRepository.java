package com.networks.tea.repository;

import java.util.Optional;

import com.networks.tea.enums.ERole;
import com.networks.tea.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}