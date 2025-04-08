package com.taskboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.taskboard.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
	 public List<Comment> findByPosts_PostsIdAndDelYn(Long PostsId, String delYn);
}
