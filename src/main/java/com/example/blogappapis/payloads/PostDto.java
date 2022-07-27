package com.example.blogappapis.payloads;


import com.example.blogappapis.entity.Post;
import lombok.*;

import java.util.Date;

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

//    public PostDto(String title) {
//        this.title = title;
//    }

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    public PostDto(Post post){
        this.category=CategoryDto.builder().categoryId(post.getCategory().getCategoryId())
                .categoryDescription(post.getCategory().getCategoryDescription())
                .categoryTitle(post.getCategory().getCategoryTitle())
                .categoryId(post.getCategory().getCategoryId())
                .build();
        this.title= post.getTitle();
        this.addedDate=post.getAddDate();
        this.content= post.getContent();
        this.imageName= post.getImageName();
        this.user=new UserDto(post.getUser());
        this.postId = post.getPostId();

    }



}
