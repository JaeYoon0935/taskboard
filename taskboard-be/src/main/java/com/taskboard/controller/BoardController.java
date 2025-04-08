package com.taskboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskboard.dto.BoardDto;
import com.taskboard.dto.PostsDto;
import com.taskboard.entity.Board;
import com.taskboard.entity.Posts;
import com.taskboard.service.BoardService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/Board")
public class BoardController {
	
	@Autowired
	BoardService boardServiceImpl;
	
	@GetMapping("/getPostsList")
	public ResponseEntity<List<PostsDto>> getPostsList() {
		List<PostsDto> postsList = boardServiceImpl.getPostsList();
        return ResponseEntity.ok(postsList);
    }
	
	@PostMapping("/regPost")
	public ResponseEntity<Posts> regPost(@RequestBody Posts param) {
		Posts posts = boardServiceImpl.regPost(param);
		return ResponseEntity.ok(posts);
	}
	
	@PostMapping("/postDetail")
	public ResponseEntity<PostsDto> postDetail(@RequestBody Posts param) {
		PostsDto postsDto = boardServiceImpl.postDetail(param);
		return ResponseEntity.ok(postsDto);
	}
	
	@PostMapping("/modPost")
	public ResponseEntity<Posts> modPost(@RequestBody Posts param) {
		Posts posts = boardServiceImpl.modPost(param);
		return ResponseEntity.ok(posts);
	}
	
	@PostMapping("/delPost")
	public ResponseEntity<Posts> delPost(@RequestBody Posts param) {
		Posts posts = boardServiceImpl.delPost(param);
		return ResponseEntity.ok(posts);
	}
	
}
