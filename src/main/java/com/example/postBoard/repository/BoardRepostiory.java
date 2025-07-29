package com.example.postBoard.repository;

import com.example.postBoard.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepostiory extends JpaRepository<BoardEntity, Integer> {
}
