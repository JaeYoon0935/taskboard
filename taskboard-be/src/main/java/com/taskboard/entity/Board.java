package com.taskboard.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

	@Column(name = "board_title")
	private String boardTitle;
	
	@Column(name="del_yn")
	private String delYn;
	
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Posts> posts = new ArrayList<>();
    
	@PrePersist
	public void prePersist() {
		
		if (this.boardId == null) { // 임시로 1번 게시판으로 하드코딩. 추후 게시판 확장되면 개발하기
			this.boardId = (long) 1;
		}
		
		if (this.delYn == null) {
			this.delYn = "N";
		}
		
	}
	
}
