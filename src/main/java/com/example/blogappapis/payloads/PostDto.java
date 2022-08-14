package com.example.blogappapis.payloads;


import com.example.blogappapis.entity.Comment;
import com.example.blogappapis.entity.Post;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;



    private CategoryDto category;

    private UserDto user;
    private List<CommentDto> comments;

    public PostDto(Post post){
        this.category=CategoryDto.builder().categoryId(post.getCategory().getCategoryId())
                .categoryDescription(post.getCategory().getCategoryDescription())
                .categoryTitle(post.getCategory().getCategoryTitle())
                .categoryId(post.getCategory().getCategoryId())
                .build();
        this.title= post.getTitle();

        this.content= post.getContent();
        this.imageName= post.getImageName();
        this.user=new UserDto(post.getUser());
        this.postId = post.getPostId();
        this.comments=post.getComments().stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());

    }



}
