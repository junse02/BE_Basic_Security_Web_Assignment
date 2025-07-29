package com.example.postBoard.dto;

import com.example.postBoard.domain.BoardEntity;
import lombok.*;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
public class BoardDto {
    private int id;
    private String title;
    private String content;
    private String writer;

    public BoardEntity toEntity() {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(id);
        boardEntity.setTitle(title);
        boardEntity.setContent(content);
        boardEntity.setWriter(writer);
        return boardEntity;
    }

}
