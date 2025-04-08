package com.taskboard.dto;

import com.taskboard.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long commentId;  
    private Long postsId;    
    private String content;
    private String regUser;  
    private String regDts;   
    
    public static CommentDto fromEntity(Comment comment) {
        return new CommentDto(
            comment.getCommentId(),
            comment.getPosts().getPostsId(), // posts ID 가져오기
            comment.getContent(),
            comment.getRegUser(),
            comment.getRegDts()
        );
    }
}
