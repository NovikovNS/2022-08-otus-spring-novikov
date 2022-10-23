package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao.BookRepository;
import ru.otus.homework7.dao.CommentRepository;
import ru.otus.homework7.domain.Comment;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final IOService ioService;
    private final CommentRepository commentRepository;
    private final MessageService messageService;

    public CommentsServiceImpl(BookRepository bookRepository, IOService ioService, CommentRepository commentRepository, MessageService messageService) {
        this.ioService = ioService;
        this.commentRepository = commentRepository;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public Comment getCommentById(long commentId) {
        return commentRepository.getReferenceById(commentId);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByBookId(long bookId) {
        return commentRepository.findCommentsByBookId(bookId);
    }

    @Override
    @Transactional
    public void saveNewComment(long bookId) {
        ioService.outputString(messageService.getMessage("creating_comment.enter_comment"));
        String commentText = ioService.readString();
        Comment comment = Comment.builder()
                .bookId(bookId)
                .comment(commentText)
                .build();
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(long commentId, String comment) {
        commentRepository.save(Comment.builder().id(commentId).comment(comment).build());
    }

    @Override
    @Transactional
    public void deleteComment(long commentId) {
        commentRepository.deleteCommentById(commentId);
    }
}
