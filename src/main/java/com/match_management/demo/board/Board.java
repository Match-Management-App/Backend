package com.match_management.demo.board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//TODO
// board에 투표 Matchday column 넣어 놓자
// 어짜피 게시판은 투표 용으로 사용될거기 때문에, voteBoard가 소용이 없음
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
    private LocalDateTime matchDate;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    @Builder
    public Board(final Long userId, final String writer, final String title, final LocalDateTime matchDate) {
        this.userId = userId;
        this.writer = writer;
        this.title = title;
        this.matchDate = matchDate;
        this.createAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void amendTitle(final String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void amendMatchDate(final LocalDateTime matchDate) {
        this.matchDate = matchDate;
        this.updatedAt = LocalDateTime.now();
    }
}
