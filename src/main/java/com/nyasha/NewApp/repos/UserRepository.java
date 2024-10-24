package com.nyasha.NewApp.repos;


import com.nyasha.NewApp.model.User;
import com.nyasha.NewApp.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);

    @Modifying
    @Query("UPDATE User u SET u.isActive = ?2 WHERE u.id = ?1")
    int updateUserActiveStatus(Long userId, boolean isActive);
}