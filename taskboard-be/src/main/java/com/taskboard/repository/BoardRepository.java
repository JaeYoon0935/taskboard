package com.taskboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.taskboard.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>{
	
}
