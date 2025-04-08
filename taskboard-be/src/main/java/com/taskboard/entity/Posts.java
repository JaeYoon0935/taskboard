package com.taskboard.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long postsId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
	
	private String title;
	
	private String content;
	
	@Column(name="reg_user")
	private String regUser;
	
	@Column(name="reg_dts")
	private String regDts;
	
	@Column(name="mod_user")
	private String modUser;
	
	@Column(name="mod_dts")
	private String modDts;
	
	@Column(name="del_yn")
	private String delYn;
	
    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
	
	@PrePersist
	public void prePersist() {
		
		if(this.regUser == null) {
			this.regUser = "user";
		}
		
		if (this.regDts == null) {
            this.regDts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
		
		if (this.delYn == null) {
			this.delYn = "N";
		}
		
	}
	
}
