package com.klu.service;


import com.klu.model.User;
import com.klu.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collections;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    public UserDetails loadUserByUsername(String username) {
        User user = repo.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
    public User saveUser(User user) {
        return repo.save(user);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    public User getUser(String username) {
        return repo.findByUsername(username);
    }
}
