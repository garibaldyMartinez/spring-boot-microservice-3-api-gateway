package com.vaxi.springbootmicroservice3apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaxi.springbootmicroservice3apigateway.security.user.Role;
import com.vaxi.springbootmicroservice3apigateway.security.user.User;
import com.vaxi.springbootmicroservice3apigateway.security.user.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService; 
    
    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole (@AuthenticationPrincipal User user, @PathVariable Role role)
    {
        userService.changeRole(role, user.getUsername());
        return ResponseEntity.ok(true);
    }
}
