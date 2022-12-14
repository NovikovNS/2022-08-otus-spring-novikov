package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao.BookRepository;
import ru.otus.homework6.dao.CommentRepository;
import ru.otus.homework6.domain.Comment;
import ru.otus.homework6.dto.CommentDto;
import ru.otus.homework6.dto.converter.CommentDtoConverter;
import ru.otus.homework6.exception.BookNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final BookRepository bookRepository;


    public CommentsServiceImpl(CommentRepository commentRepository,
                               CommentDtoConverter commentDtoConverter, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(int commentId) {
        return commentRepository.getCommentById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByBookId(int bookId) {
        return bookRepository.getBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Not found book with bookId:%s", bookId)))
                .getComments().stream().map(commentDtoConverter::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveNewComment(CommentDto newComment) {
        commentRepository.save(commentDtoConverter.mapToEntity(newComment));
    }

    @Override
    @Transactional
    public void updateComment(CommentDto updatingComment) {
        commentRepository.save(commentDtoConverter.mapToEntity(updatingComment));
    }

    @Override
    @Transactional
    public void deleteComment(int commentId) {
        commentRepository.deleteComment(commentId);
    }
}
