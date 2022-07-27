package com.example.blogappapis;

import com.example.blogappapis.repositoreis.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {
    @Autowired
    private UserRepo userRepo;

    @Test
    void contextLoads() {
    }
    @Test
    public void repoTest(){
        String name = userRepo.getClass().getName();
        String packageName = userRepo.getClass().getPackageName();
        System.out.println("this is class name =" + name);
        System.out.println("this is package name = "+ packageName);

    }

}
