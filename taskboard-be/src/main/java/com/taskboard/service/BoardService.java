package com.taskboard.service;

import java.util.List;

import com.taskboard.dto.PostsDto;
import com.taskboard.entity.Posts;

public interface BoardService {
	
	public List<PostsDto> getPostsList();
	
	public Posts regPost(Posts param);
	
	public PostsDto postDetail(Posts param);

	public Posts modPost(Posts param);

	public Posts delPost(Posts param);

}
