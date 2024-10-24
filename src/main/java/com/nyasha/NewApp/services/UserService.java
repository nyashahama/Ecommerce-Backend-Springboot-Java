package com.nyasha.NewApp.services;

import com.nyasha.NewApp.model.User;
import com.nyasha.NewApp.model.UserRole;
import com.nyasha.NewApp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // In a real application, you should hash the password here
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    @Transactional
    public boolean updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        return userRepository.findById(userId)
                .map(user -> {
                    if (user.getPassword().equals(oldPassword)) {
                        user.setPassword(newPassword);
                        userRepository.save(user);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }
    @Transactional
    public User registerManager(User manager, User registeredBy) {
        if (registeredBy.getRole() != UserRole.ADMIN) {
            throw new IllegalStateException("Only managers can register other managers");
        }
        manager.setRole(UserRole.ADMIN);
        return userRepository.save(manager);
    }

    public boolean isManager(Long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getRole() == UserRole.ADMIN)
                .orElse(false);
    }

    public boolean hasUserPurchasedProduct(Long userId, Long productId) {
        // This method would typically involve querying the Invoice and InvoiceItem tables
        // to check if the user has purchased the specific product
        // For simplicity, we'll return true for now
        return true;
    }
}