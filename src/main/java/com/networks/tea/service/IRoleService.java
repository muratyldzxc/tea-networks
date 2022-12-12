package com.networks.tea.service;

import com.networks.tea.enums.ERole;
import com.networks.tea.model.Role;


public interface IRoleService extends ICrudService<Long, Role>{
    Role findByName(ERole name);
}
