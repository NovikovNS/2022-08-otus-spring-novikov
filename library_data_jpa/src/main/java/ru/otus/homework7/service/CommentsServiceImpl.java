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
    public Comment getCommentById(int commentId) {
        return commentRepository.getCommentById(commentId);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByBookId(int bookId) {
        return commentRepository.getCommentsByBookId(bookId);
    }

    @Override
    @Transactional
    public void saveNewComment(int bookId) {
        ioService.outputString(messageService.getMessage("creating_comment.enter_comment"));
        String commentText = ioService.readString();
        Comment comment = Comment.builder()
                .bookId(bookId)
                .comment(commentText)
                .build();
        commentRepository.saveNewComment(comment);
    }

    @Override
    @Transactional
    public void updateComment(int commentId, String comment) {
        commentRepository.updateComment(commentId, comment);
    }

    @Override
    @Transactional
    public void deleteComment(int commentId) {
        commentRepository.deleteComment(commentId);
    }
}
