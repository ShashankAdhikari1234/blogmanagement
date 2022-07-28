package com.example.blogappapis.services;

import com.example.blogappapis.entity.Comment;
import com.example.blogappapis.entity.Post;
import com.example.blogappapis.exception.ResourceNotFoundException;
import com.example.blogappapis.payloads.CommentDto;
import com.example.blogappapis.repositoreis.CommentRepo;
import com.example.blogappapis.repositoreis.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(

                ()->new ResourceNotFoundException("post","postId",postId)
        );
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);



        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment com = commentRepo.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("comment","commentId",commentId)
        );


    }
}
