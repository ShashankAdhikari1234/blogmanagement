package com.example.blogappapis.repositoreis;

import com.example.blogappapis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
