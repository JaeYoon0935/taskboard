package com.taskboard.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.taskboard.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long boardId;
    private String boardTitle;
    private String delYn;
    
    public static BoardDto fromEntity(Board board) {
        return new BoardDto(
            board.getBoardId(),
            board.getBoardTitle(),
            board.getDelYn()
            /*
            ,
            board.getComments().stream()
            .map(comment -> new CommentDto(comment.getCommentId(), comment.getBoard().getBoardId(), comment.getContent(), comment.getRegUser(), comment.getRegDts()))
            .collect(Collectors.toList())
            */
        );
    }
}
