package com.example.blogappapis.services;

import com.example.blogappapis.entity.Post;
import com.example.blogappapis.payloads.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    PostDto getPostById(Integer postId);

    //get aLL POST by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostByUser(Integer userId);

    //search
    List<Post> searchPosts(String keyword);


}
