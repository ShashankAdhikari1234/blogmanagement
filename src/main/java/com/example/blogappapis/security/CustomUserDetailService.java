package com.example.blogappapis.security;


import com.example.blogappapis.entity.User;
import com.example.blogappapis.exception.ResourceNotFoundException;
import com.example.blogappapis.repositoreis.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  User user =userRepo.findByEmail(username).orElseThrow(
          ()-> new ResourceNotFoundException("user","email:"+username,0)
  );

        return user;
    }
}
