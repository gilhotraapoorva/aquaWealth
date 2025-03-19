
package com.aquawealth.service;

import com.aquawealth.model.User;
import com.aquawealth.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public boolean userExists(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // New Method to Fetch User by Government ID (Returns null if not found)
    public User getUserByGovernmentId(String governmentId) {
        if (governmentId == null || governmentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Government ID cannot be empty");
        }

        String cleanGovernmentId = governmentId.trim().toLowerCase();
        log.info("Fetching user with government_id: {}", cleanGovernmentId);

        return userRepository.findByGovernmentId(cleanGovernmentId).orElse(null);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
