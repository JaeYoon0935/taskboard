package com.taskboard.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskboard.dto.BoardDto;
import com.taskboard.dto.PostsDto;
import com.taskboard.entity.Board;
import com.taskboard.entity.Posts;
import com.taskboard.repository.BoardRepository;
import com.taskboard.repository.PostsRepository;
import com.taskboard.service.BoardService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Override
    public List<PostsDto> getPostsList() {
        return postsRepository.getPostsList();
    }

	@Override
	@Transactional
	public Posts regPost(Posts param) {
		
		Board board = new Board(); // 임시로 1번 게시판, 추후 확장개발하기.
		board.setBoardId(1L);
		
		Posts posts = new Posts();
		posts.setBoard(board);
		posts.setTitle(param.getTitle());
		posts.setContent(param.getContent());
		posts.setRegUser(param.getRegUser());
		return postsRepository.save(posts);
	}

	@Override
	public PostsDto postDetail(Posts param) {
		Posts posts = postsRepository.findById(param.getPostsId()).orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. ID " + param.getPostsId()));
		return PostsDto.fromEntity(posts);
	}
	
	@Override
	@Transactional
	public Posts modPost(Posts param) {
		Long postsId = param.getPostsId();
		
	    // 기존 게시글 조회
	    Posts posts = postsRepository.findById(postsId).orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다: " + postsId));
		
	    String modDts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    
	    posts.setModUser("user"); //임시로 수정자 user 하드코딩
	    posts.setModDts(modDts);
	    posts.setTitle(param.getTitle());
	    posts.setContent(param.getContent());

	    return postsRepository.save(posts);
	}
	
	@Override
	@Transactional
	public Posts delPost(Posts param) {
	    Long postsId = param.getPostsId();

	    Posts posts = postsRepository.findById(postsId).orElseThrow(()-> new EntityNotFoundException("게시글을 찾을 수 없습니다: " + postsId));
	    String modDts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    
	    posts.setDelYn("Y");
	    posts.setModDts(modDts);
	    return postsRepository.save(posts);
	    
	    //실제 delete 로직이며 테스트 해보았음.
	    /*
	    Board board = boardRepository.findById(boardId)
	        .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다: " + boardId));

	    boardRepository.delete(board);
	    
	    return board; // 삭제된 엔티티 반환
	    */
	}
	
}
