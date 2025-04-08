package com.taskboard.service;

import java.util.List;

import com.taskboard.dto.CommentDto;
import com.taskboard.entity.Board;

public interface CommentService {

	public List<CommentDto> getComments(CommentDto param);
	
	public CommentDto regComment(CommentDto param);
	
	public CommentDto delComment(CommentDto param);
}
