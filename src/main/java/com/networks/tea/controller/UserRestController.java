package com.networks.tea.controller;

import com.networks.tea.dto.UserDto;
import com.networks.tea.model.User;
import com.networks.tea.payload.request.SignUpRequest;
import com.networks.tea.payload.response.MessageResponse;
import com.networks.tea.service.service_impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserRestController {

    UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getById(@PathVariable UUID user_id) {
        User user = userService.findById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateById(@RequestBody UserDto userDto, @PathVariable UUID user_id) {
        User updatedUser;
        updatedUser = userService.updateById(user_id, userDto);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID user_id) {
        userService.deleteById(user_id);
        return ResponseEntity.ok(new MessageResponse("Deleted Successfully"));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User savedUser =  userService.add(signUpRequest);

        return ResponseEntity.ok(savedUser);
    }

}
