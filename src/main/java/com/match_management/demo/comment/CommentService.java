package com.match_management.demo.comment;

import java.util.Comparator;
import java.util.List;

import com.match_management.demo.comment.dto.CommentsResponse;
import com.match_management.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long create(final Long userId, final Long boardId, final String text) {
        final Comment comment = Comment.builder().userId(userId).boardId(boardId).text(text).build();

        commentRepository.save(comment);
        return comment.getId();
    }

    public List<CommentsResponse> viewAllComments(final Long boardId) {
        final List<Comment> commentList = commentRepository.findAllByBoardId(boardId);
        commentList.sort(Comparator.comparing(Comment::getUpdatedAt));
        return commentList.stream()
                .map(c -> CommentsResponse
                        .builder()
                        .userName(userRepository.findById(c.getUserId()).orElseThrow(RuntimeException::new).getName())
                        .text(c.getText())
                        .date(c.getUpdatedAt())
                        .build()
                )
                .toList();
    }

    @Transactional
    public void amend(final Long commentId, final String amendText) {
        final Comment comment = commentRepository.findById(commentId)
                .orElseThrow(RuntimeException::new);

        comment.amend(amendText);
    }
}
