package com.example.blogappapis.repositoreis;

import com.example.blogappapis.entity.Category;
import com.example.blogappapis.entity.Post;
import com.example.blogappapis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post>findByCategory(Category category);

}
