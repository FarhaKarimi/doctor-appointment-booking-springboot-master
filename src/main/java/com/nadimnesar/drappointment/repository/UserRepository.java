package com.nadimnesar.drappointment.repository;

import com.nadimnesar.drappointment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameIgnoreCase(String username);
}