package com.klu.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
