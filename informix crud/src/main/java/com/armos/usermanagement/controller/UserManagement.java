package com.armos.usermanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.armos.usermanagement.model.User;
import com.armos.usermanagement.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserManagement
 */
@RestController
public class UserManagement {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/user")
    public List<User> getalluser() {
        return userRepository.findAll();
    }

    @PostMapping("/userpost")
    public User inputuser(@Valid @RequestBody User user) {
        System.out.println(user);
        return userRepository.save(user);
        
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateuser(@PathVariable(value = "id") Long userId,
            @Valid @RequestBody User useredetail) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found on ::" + userId));
    
        user.setFirstname(useredetail.getFirstname());
        user.setLastname(useredetail.getLastname());
        user.setPassword(useredetail.getPassword());
        user.setUsername(useredetail.getUsername());
        final User updateduser= userRepository.save(user);
        return ResponseEntity.ok(updateduser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteuser(@PathVariable(value = "id") Long userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not Found on::" + userId));
                                         userRepository.delete(user);
        Map<String, Boolean> response  = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}