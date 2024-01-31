package com.spring.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.demo.entities.User;
import com.spring.demo.repositories.UserRepository;

@Controller
@RequestMapping("/api/user")
public class UserController {
	
	private final UserRepository repository;
	private PasswordEncoder encoder;

    UserController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
	
    @GetMapping("/listAll")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}
	
    @PostMapping()
	public ResponseEntity<User> insert(@RequestBody User user) {
    	user.setPassword(encoder.encode(user.getPassword()));
		return ResponseEntity.ok().body(repository.save(user));
	}
    
    @GetMapping("/validateUser")
    public ResponseEntity<Boolean> validateUser (@RequestParam String login, @RequestParam String password) {
    	Optional<User> optUser = repository.findByLogin(login);
    	if(optUser.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    	}
    	
    	boolean valid = encoder.matches(password, optUser.get().getPassword());
    	
    	HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
    	return ResponseEntity.status(status).body(valid);
    }
}
