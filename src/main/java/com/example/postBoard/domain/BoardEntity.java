package com.example.postBoard.domain;

import com.example.postBoard.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class BoardEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @Column(length = 1000)
    private String content;
    private String writer;

    private LocalDateTime createdAt;

    public void updateBoard(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.writer = boardDto.getWriter();
    }
}
