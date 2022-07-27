package com.example.blogappapis.services;

import com.example.blogappapis.entity.Category;
import com.example.blogappapis.entity.Post;
import com.example.blogappapis.entity.User;
import com.example.blogappapis.exception.ResourceNotFoundException;
import com.example.blogappapis.payloads.PostDto;
import com.example.blogappapis.repositoreis.CategoryRepo;
import com.example.blogappapis.repositoreis.PostRepo;
import com.example.blogappapis.repositoreis.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user id",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category id",categoryId));
        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
       /* PostDto map = this.modelMapper.map(newPost, PostDto.class);
        map.setAddedDate(newPost.getAddDate());
        return map;

*/
   return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(post.getImageName());
        Post updatepost =postRepo.save(post);


        return new PostDto(updatepost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "postId", postId));
        postRepo.delete(post);



    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> all = postRepo.findAll();
        List<PostDto> postDtos = all.stream().map((post) -> new PostDto(post)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "postId", postId));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category cat = categoryRepo.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("category", "categoryId",categoryId)
        );
        List<Post> posts = postRepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map(post -> new PostDto(post)).collect(Collectors.toList());


        return postDtos ;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
       User user = userRepo.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("user", "userId",userId)
        );
        List<Post> byUser = postRepo.findByUser(user);
    List<PostDto> postDtos = byUser.stream().map((post) ->new PostDto(post)).collect(Collectors.toList());
    return postDtos;


    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
