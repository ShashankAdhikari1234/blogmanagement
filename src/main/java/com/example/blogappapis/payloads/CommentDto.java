package com.example.blogappapis.payloads;

import com.example.blogappapis.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int id;
    private String content;
    public CommentDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
