package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao.CommentRepository;
import ru.otus.homework6.domain.Comment;
import ru.otus.homework6.dto.CommentDto;
import ru.otus.homework6.dto.converter.CommentDtoConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;


    public CommentsServiceImpl(CommentRepository commentRepository,
                               CommentDtoConverter commentDtoConverter) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(int commentId) {
        return commentRepository.getCommentById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByBookId(int bookId) {
        return commentRepository.getCommentsByBookId(bookId)
                .stream().map(commentDtoConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveNewComment(CommentDto newComment) {
        commentRepository.save(commentDtoConverter.fromDto(newComment));
    }

    @Override
    @Transactional
    public void updateComment(CommentDto updatingComment) {
        commentRepository.save(commentDtoConverter.fromDto(updatingComment));
    }

    @Override
    @Transactional
    public void deleteComment(int commentId) {
        commentRepository.deleteComment(commentId);
    }
}
