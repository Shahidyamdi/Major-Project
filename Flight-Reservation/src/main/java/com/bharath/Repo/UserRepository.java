package com.bharath.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);



}
