package com.vaxi.springbootmicroservice3apigateway.security.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        
        return repository.save(user);
    }

    public Optional<User> findByUsername(String username)
    {
        return repository.findByEmail(username);
    }

    @Transactional
    public void changeRole(Role newRole, String username)
    {
        repository.updateUserRole(username, newRole);
    }
 
    
}
