package com.example.postBoard.service;

import com.example.postBoard.domain.BoardEntity;
import com.example.postBoard.dto.BoardDto;
import com.example.postBoard.repository.BoardRepostiory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepostiory boardRepostiory;

    // 게시글 저장
    @Transactional
    public void saveBoard(BoardDto boardDto) {
        BoardEntity boardEntity = boardDto.toEntity();
        boardRepostiory.save(boardEntity);
    }

    // 전체 게시글 목록
    @Transactional
    public List<BoardDto> findAllList() {
        List<BoardEntity> boardEntityList = boardRepostiory.findAll();

        ArrayList<BoardDto> boardDtoArrayList = new ArrayList<>();

        for (BoardEntity board : boardEntityList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .writer(board.getWriter())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdAt(board.getCreatedAt())
                    .build();

            boardDtoArrayList.add(boardDto);
        }
        return boardDtoArrayList;
    }

    // 특정 게시글
    @Transactional
    public BoardDto findById(int id) {
        Optional<BoardEntity> boardEntityOptional = boardRepostiory.findById(id);
        BoardEntity boardEntity = boardEntityOptional.get();

        if (boardEntityOptional.isPresent()){
            BoardDto boardDto = BoardDto.builder()
                    .id(boardEntity.getId())
                    .writer(boardEntity.getWriter())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .createdAt(boardEntity.getCreatedAt())
                    .build();

            return boardDto;
        }
        else {
            return null;
        }
    }

    // 게시글 수정
    @Transactional
    public void update(int id, BoardDto boardDto) {
        BoardEntity boardEntity = boardRepostiory.findById(id).get();
        boardEntity.updateBoard(boardDto);
    }

    // 게시글 삭제
    @Transactional
    public void deleteById(int id) {
        boardRepostiory.deleteById(id);
    }
}
