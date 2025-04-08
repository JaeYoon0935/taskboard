package com.taskboard.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.taskboard.entity.Board;
import com.taskboard.entity.Posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsDto {
	private Long postsId;
    private Long boardId;
    private String title;
    private String content;
    private String regUser;
    private String regDts;
    private String modUser;
    private String modDts;
    private String delYn;
    
    public static PostsDto fromEntity(Posts posts) {
        return new PostsDto(
    		posts.getPostsId(),
    		posts.getBoard().getBoardId(),
    		posts.getTitle(),
    		posts.getContent(),
    		posts.getRegUser(),
    		posts.getRegDts(),
    		posts.getModUser(),
    		posts.getModDts(),
    		posts.getDelYn()
        );
    }
}
