package com.example.blogappapis;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Blog API",
                version = "v1",
                description = "This app provides REST APIs for Blog ",
                contact = @Contact(
                        name = "shashank adhikari",
                        email = "shashankadhikari1234@gmail.com"
                                     )
        ),
        servers = {

                @Server(
                        url = "http://localhost:8090/",
                        description = "Local Server"
                )
        }
)
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class BlogAppApisApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("1234"));
    }
}
