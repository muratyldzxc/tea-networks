package com.networks.tea.dto;

import com.networks.tea.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    @Size(max = 30)
    private String name;

    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @Size(min = 6, max = 40)
    private String password;

    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    private Set<Role> roles;

}