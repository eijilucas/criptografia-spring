package com.spring.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByLogin(String login);
}
