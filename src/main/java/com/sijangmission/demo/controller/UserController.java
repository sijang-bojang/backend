package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.User;
import com.sijangmission.demo.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Operation(summary = "모든 유저 조회")
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Operation(summary = "유저 등록")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
