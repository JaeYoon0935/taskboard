package com.taskboard.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.taskboard.dto.PostsDto;
import com.taskboard.entity.Board;
import com.taskboard.entity.Posts;
import com.taskboard.repository.BoardRepository;
import com.taskboard.repository.PostsRepository;
import com.taskboard.service.impl.BoardServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BoardServiceImplTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private PostsRepository postsRepository;

    @InjectMocks
    private BoardServiceImpl boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegPost() {
        // given
        Posts input = new Posts();
        input.setTitle("Test Title");
        input.setContent("Test Content");

        Posts saved = new Posts();
        saved.setPostsId(1L);
        saved.setTitle("Test Title");
        saved.setContent("Test Content");

        when(postsRepository.save(any(Posts.class))).thenReturn(saved);

        // when
        Posts result = boardService.regPost(input);

        // then
        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Content", result.getContent());
    }

    @Test
    void testPostDetail_success() {
    	
    	Board board = new Board();
    	board.setBoardId(1L);
    	
        Posts post = new Posts();
        post.setPostsId(1L);
        post.setTitle("Title");
        post.setContent("Content");
        post.setBoard(board);

        when(postsRepository.findById(1L)).thenReturn(Optional.of(post));

        Posts param = new Posts();
        param.setPostsId(1L);

        // when
        PostsDto result = boardService.postDetail(param);

        // then
        assertEquals("Title", result.getTitle());
        assertEquals("Content", result.getContent());
    }

    @Test
    void testPostDetail_notFound() {
        // given
        Posts param = new Posts();
        param.setPostsId(99L);

        when(postsRepository.findById(99L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(EntityNotFoundException.class, () -> boardService.postDetail(param));
    }

    @Test
    void testModPost_success() {
        // given
        Posts original = new Posts();
        original.setPostsId(1L);
        original.setTitle("Old");
        original.setContent("Old Content");

        Posts update = new Posts();
        update.setPostsId(1L);
        update.setTitle("New Title");
        update.setContent("New Content");

        when(postsRepository.findById(1L)).thenReturn(Optional.of(original));
        when(postsRepository.save(any(Posts.class))).thenAnswer(inv -> inv.getArgument(0));

        // when
        Posts result = boardService.modPost(update);

        // then
        assertEquals("New Title", result.getTitle());
        assertEquals("New Content", result.getContent());
        assertEquals("user", result.getModUser());
        assertNotNull(result.getModDts());
    }

    @Test
    void testDelPost_success() {
        // given
        Posts original = new Posts();
        original.setPostsId(1L);

        when(postsRepository.findById(1L)).thenReturn(Optional.of(original));
        when(postsRepository.save(any(Posts.class))).thenAnswer(inv -> inv.getArgument(0));

        Posts param = new Posts();
        param.setPostsId(1L);

        // when
        Posts result = boardService.delPost(param);

        // then
        assertEquals("Y", result.getDelYn());
        assertNotNull(result.getModDts());
    }
}
