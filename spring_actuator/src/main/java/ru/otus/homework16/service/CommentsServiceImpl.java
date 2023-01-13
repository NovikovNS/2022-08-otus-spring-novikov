package ru.otus.homework16.service;

import org.springframework.stereotype.Service;
import ru.otus.homework16.dao.CommentRepository;
import ru.otus.homework16.rest.dto.CommentDto;
import ru.otus.homework16.rest.dto.converter.CommentDtoConverter;

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
    public CommentDto getCommentById(long commentId) {
        return commentDtoConverter.mapToDto(commentRepository.findById(commentId).get());
    }

    @Override
    public List<CommentDto> getCommentsByBookId(long bookId) {
        return commentRepository.findCommentsByBookId(bookId)
                .stream().map(commentDtoConverter::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void saveNewComment(CommentDto newComment) {
        commentRepository.save(commentDtoConverter.mapToEntity(newComment));
    }

    @Override
    public void updateComment(CommentDto updatingComment) {
        commentRepository.save(commentDtoConverter.mapToEntity(updatingComment));
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
