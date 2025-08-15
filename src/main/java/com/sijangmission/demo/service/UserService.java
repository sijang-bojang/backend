package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.User;
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
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    @Transactional
    public User updateUserReward(Long userId, Integer rewardPoints) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setTotalReward(user.getTotalReward() + rewardPoints);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }
    
    @Transactional
    public User updateUserExp(Long userId, Integer exp) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setExp(user.getExp() + exp);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }
}
