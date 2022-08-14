package com.example.blogappapis.controllers;


import com.example.blogappapis.payloads.ApiResponse;
import com.example.blogappapis.payloads.CommentDto;
import com.example.blogappapis.services.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {
    CommentDto createComment = commentService.createComment(comment, postId);
    return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
}
@DeleteMapping("/comments/{commentId}")
   public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId) {
    commentService.deleteComment(commentId);
    return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted",true),HttpStatus.OK);


}
}
