package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao.BookRepository;
import ru.otus.homework6.dao.CommentsRepository;
import ru.otus.homework6.domain.Comment;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final BookRepository bookRepository;
    private final IOService ioService;
    private final CommentsRepository commentsRepository;
    private final MessageService messageService;

    public CommentsServiceImpl(BookRepository bookRepository, IOService ioService, CommentsRepository commentsRepository, MessageService messageService) {
        this.bookRepository = bookRepository;
        this.ioService = ioService;
        this.commentsRepository = commentsRepository;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByBookId(int bookId) {
        return commentsRepository.getCommentsByBookId(bookId);
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
        commentsRepository.saveNewComment(comment);
    }
}
