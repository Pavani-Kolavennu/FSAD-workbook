package com.klu.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
