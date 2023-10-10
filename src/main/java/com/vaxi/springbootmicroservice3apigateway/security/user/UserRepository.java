package com.vaxi.springbootmicroservice3apigateway.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import feign.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

     Optional<User> findByEmail(String email);

     @Modifying
     @Query("update User set role=:role where email=:username")
     void updateUserRole(@Param("username") String username, @Param("role") Role role);
}
