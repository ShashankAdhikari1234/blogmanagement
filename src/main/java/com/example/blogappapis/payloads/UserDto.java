package com.example.blogappapis.payloads;


import com.example.blogappapis.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4, message = "UserName must be min of 4 character")
    private String name;
    @NotEmpty
    @Email(message = "email address is not valid!!")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "password must be min of 3 chars and max of 10 chars")
    private String password;
    @NotEmpty
    private String about;

    public UserDto(User user){
        this.id= user.getId();
        this.about= user.getAbout();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();

    }

}
