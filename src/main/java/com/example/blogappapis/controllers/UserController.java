package com.example.blogappapis.controllers;

import com.example.blogappapis.payloads.ApiResponse;
import com.example.blogappapis.payloads.UserDto;
import com.example.blogappapis.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

@PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto creatUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(creatUserDto,HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok().body(updatedUser);



    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
    this.userService.deleteUser(userId);
    return new ResponseEntity<>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
    return ResponseEntity.ok(this.userService.getAllUsers());

    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));

    }


}
