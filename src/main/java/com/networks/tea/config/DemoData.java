package com.networks.tea.config;

import com.networks.tea.enums.ERole;
import com.networks.tea.model.Role;
import com.networks.tea.model.User;
import com.networks.tea.service.service_impl.RoleService;
import com.networks.tea.service.service_impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DemoData implements CommandLineRunner {

    private final RoleService roleService;

    private final UserService userService;

    private final PasswordEncoder encoder;

    @Autowired
    public DemoData(RoleService roleService, UserService userService, PasswordEncoder encoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public void run(String...args) throws Exception {
        // Default roles added
        Role userRole = new Role();
        userRole.setName(ERole.ROLE_USER);

        Role adminRole = new Role();
        adminRole.setName(ERole.ROLE_ADMIN);

        roleService.add(userRole);
        Role savedAdminRole = roleService.add(adminRole);

        // Default admin added
        Set<Role> roles = new HashSet<>();
        roles.add(savedAdminRole);

        User adminUser = new User();
        adminUser.setName("admin1");
        adminUser.setUsername("admin1");
        adminUser.setPassword(encoder.encode("passwordadmin1"));
        adminUser.setEmail("admin1@gmail.com");
        adminUser.setRoles(roles);

        userService.add(adminUser);
    }
}