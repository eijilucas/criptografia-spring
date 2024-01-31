package com.spring.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.demo.entities.User;
import com.spring.demo.repositories.UserRepository;

@Controller
@RequestMapping("/api/user")
public class UserController {
	
	public final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }
	
    @GetMapping("/listAll")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}
	
    @PostMapping()
	public ResponseEntity<User> insert(@RequestBody User user) {
		return ResponseEntity.ok().body(repository.save(user));
	}
    
}
