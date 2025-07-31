package com.example.postBoard.controller;

import com.example.postBoard.dto.BoardDto;
import com.example.postBoard.dto.ResponseDto;
import com.example.postBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody BoardDto boardDto) {
        boardService.saveBoard(boardDto);
        return ResponseEntity.ok(new ResponseDto(200, "글 작성 성공", null));
    }

    // 전체 게시글 목록 조회
    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<BoardDto> boardList = boardService.findAllList();
        return ResponseEntity.ok(new ResponseDto(200, "전체 글 목록 조회 성공", boardList));
    }

    // 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable int postId) {
        BoardDto boardDto = boardService.findById(postId);
        if (boardDto != null) {
            return ResponseEntity.ok(new ResponseDto(200, "글 조회 성공", boardDto));
        }
        return ResponseEntity.status(404).body(new ResponseDto(404, "글을 찾을 수 없습니다.", null));
    }

    // 게스글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable int postId, @RequestBody BoardDto boardDto) {
        boardService.update(postId, boardDto);
        return ResponseEntity.ok(new ResponseDto(200, "글 수정 성공", null));
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        boardService.deleteById(postId);
        return ResponseEntity.ok(new ResponseDto(200, "글 삭제 성공", null));
    }
}
