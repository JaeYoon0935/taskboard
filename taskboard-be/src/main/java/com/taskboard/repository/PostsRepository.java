package com.taskboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskboard.dto.PostsDto;
import com.taskboard.entity.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Long>{
	// JPQL
	@Query("SELECT new com.taskboard.dto.PostsDto(p.postsId, p.board.boardId, p.title, p.content, p.regUser, p.regDts, p.modUser, p.modDts, p.delYn) FROM Posts p WHERE p.delYn = 'N'")
	public List<PostsDto> getPostsList();
}
