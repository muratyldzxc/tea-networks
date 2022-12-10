package com.networks.tea.controller;

import com.networks.tea.dto.UserDto;
import com.networks.tea.model.User;
import com.networks.tea.payload.response.MessageResponse;
import com.networks.tea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{user_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getById(@PathVariable UUID user_id) {
        return new ResponseEntity<>(userRepository.findById(user_id).get(), HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateById(@RequestBody UserDto userDto, @PathVariable UUID user_id) {
        User user = userRepository.findById(user_id).get();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles());

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID user_id) {
        userRepository.deleteById(user_id);
        return ResponseEntity.ok(new MessageResponse("Deleted Successfully"));
    }

}
