package com.taskboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskboard.dto.CommentDto;
import com.taskboard.entity.Board;
import com.taskboard.entity.Comment;
import com.taskboard.service.BoardService;
import com.taskboard.service.CommentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/Comment")
public class CommentController {
	
	@Autowired
	CommentService commentServiceImpl;
	
	@PostMapping("/getComments")
	public ResponseEntity<List<CommentDto>> getList(@RequestBody CommentDto param) {
		List<CommentDto> comments = commentServiceImpl.getComments(param);
        return ResponseEntity.ok(comments);
    }
	
	@PostMapping("/regComment")
	public ResponseEntity<CommentDto> regComment(@RequestBody CommentDto param) {
		CommentDto commentDto = commentServiceImpl.regComment(param);
		return ResponseEntity.ok(commentDto);
	}
	
	@PostMapping("/delComment")
	public ResponseEntity<CommentDto> delComment(@RequestBody CommentDto param) {
		CommentDto commentDto = commentServiceImpl.delComment(param);
		return ResponseEntity.ok(commentDto);
	}
	
}
