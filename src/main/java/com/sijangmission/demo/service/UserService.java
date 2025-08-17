package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.User;
import com.sijangmission.demo.dto.UserDto;
import com.sijangmission.demo.mapper.UserMapper;
import com.sijangmission.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }
    
    public Optional<UserDto> getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(userMapper::toDto);
    }
    
    public Optional<UserDto> getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(userMapper::toDto);
    }
    
    public Optional<UserDto> getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userMapper::toDto);
    }
    
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Transactional
    public UserDto saveUser(User user) {
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
    
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    @Transactional
    public UserDto updateUserReward(Long userId, Integer rewardPoints) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setTotalReward(user.getTotalReward() + rewardPoints);
            User savedUser = userRepository.save(user);
            return userMapper.toDto(savedUser);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }
    
    @Transactional
    public UserDto updateUserExp(Long userId, Integer exp) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setExp(user.getExp() + exp);
            User savedUser = userRepository.save(user);
            return userMapper.toDto(savedUser);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }
}
