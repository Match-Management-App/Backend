package com.match_management.demo.board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String writer;
    private boolean voteBoard;
    private LocalDateTime updatedAt;

    public Board(final Long userId, final String writer, final String title, final boolean voteBoard) {
        this.userId = userId;
        this.writer = writer;
        this.title = title;
        this.voteBoard = voteBoard;
        this.updatedAt = LocalDateTime.now();
    }

    public void amendTitle(final String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }
}
