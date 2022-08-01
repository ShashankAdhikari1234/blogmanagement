package com.example.blogappapis.repositoreis;

import com.example.blogappapis.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);


}
