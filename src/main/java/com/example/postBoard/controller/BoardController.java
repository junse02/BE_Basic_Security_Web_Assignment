package com.example.postBoard.controller;

import com.example.postBoard.domain.BoardEntity;
import com.example.postBoard.dto.BoardDto;
import com.example.postBoard.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    // 기본 페이지는 게시글 목록 페이지를 보여줌
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("boardList", boardService.findAllList());
        return "board/list";
    }

    // 게시글 작성 요청시 게시글 작성 폼 띄우기
    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "board/post";
    }

    // 게시글 제목이 입력되어 있다면 작성한 게시글 데이터 전송
    @PostMapping("/post")
    public String save(@Valid BoardDto boardDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/post";
        }
        boardService.saveBoard(boardDto);
        return "redirect:/";
    }

    // 게시글 목록 보기 요청시
    @GetMapping("/boards")
    public String getBoardList(Model model) {
        List<BoardDto> boardList = boardService.findAllList();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    // 특정 게시글 상세 조회하기
    @GetMapping("/post/{id}")
    public String findById(@PathVariable int id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("boardDto", boardDto);
        return "board/detail";
    }

    // 수정할 게시글의 id를 통해 해당 게시글 데이터 가져오기
    @GetMapping("/post/update/{id}")
    public String update(@PathVariable int id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("boardDto", boardDto);
        return "board/update";
    }

    // 게시글 데이터 수정
    @PutMapping("/post/update/{id}")
    public String update(int id, BoardDto boardDto) {
        boardService.update(id, boardDto);
        return "redirect:/post/{id}";
    }

    // 게시글 삭제
    @GetMapping("/post/delete/{id}")
    public String delete(@PathVariable int id) {
        boardService.deleteById(id);
        return "redirect:/";
    }
}
