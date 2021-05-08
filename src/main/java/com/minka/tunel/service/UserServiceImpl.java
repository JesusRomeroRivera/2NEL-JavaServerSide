package com.minka.tunel.service;

import com.minka.tunel.domain.model.User;
import com.minka.tunel.domain.repository.UserRepository;
import com.minka.tunel.domain.service.UserService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        return userRepository.findById(userId)
                .map(user1 -> {
                    user1.setEmail(user.getEmail());
                    user1.setPassword(user.getPassword());
                    return userRepository.save(user1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }
}
