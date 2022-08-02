package com.example.blogappapis.controllers;

import com.example.blogappapis.payloads.ApiResponse;
import com.example.blogappapis.payloads.PostDto;
import com.example.blogappapis.payloads.UserDto;
import com.example.blogappapis.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> posts = postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }
    @GetMapping("/")
     public ResponseEntity<List<PostDto>> getAllPost(){
      return ResponseEntity.ok(postService.getAllPost());
     }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postById = postService.getPostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);


    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("post is successfully delted",true),HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatePost = postService.updatePost(postDto,postId);
        return ResponseEntity.ok().body(updatePost);



    }
}
