package com.networks.tea.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 30)
    private String name;


    @NotBlank
    @NotNull
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;


}