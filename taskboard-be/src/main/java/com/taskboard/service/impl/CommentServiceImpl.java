package com.taskboard.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskboard.dto.CommentDto;
import com.taskboard.entity.Comment;
import com.taskboard.entity.Posts;
import com.taskboard.repository.BoardRepository;
import com.taskboard.repository.CommentRepository;
import com.taskboard.repository.PostsRepository;
import com.taskboard.service.CommentService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Override
	@Transactional
	public List<CommentDto> getComments(CommentDto param) {
		
		Long postsId = param.getPostsId();
		List<Comment> comments = commentRepository.findByPosts_PostsIdAndDelYn(postsId, "N");
		return comments.stream().map(CommentDto::fromEntity).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public CommentDto regComment(CommentDto param) {
		Posts posts = postsRepository.findById(param.getPostsId()).orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
		
		Comment comment = new Comment();
		comment.setPosts(posts);
		comment.setContent(param.getContent());
		commentRepository.save(comment);
		return CommentDto.fromEntity(comment);
	}
	
	
	@Override
	@Transactional
	public CommentDto delComment(CommentDto param) {
		Long commentId = param.getCommentId();
	    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다: " + commentId));

	    comment.setDelYn("Y"); //논리 삭제 처리
	    comment.setModDts(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	    commentRepository.save(comment);
	    
	    return CommentDto.fromEntity(comment);
	}
	
	
	
}
