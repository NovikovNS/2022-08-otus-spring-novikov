package ru.otus.homework8.service;

import org.springframework.stereotype.Service;
import ru.otus.homework8.dao.BookRepository;
import ru.otus.homework8.dao.CommentRepository;
import ru.otus.homework8.domain.Book;
import ru.otus.homework8.domain.Comment;
import ru.otus.homework8.dto.CommentDto;
import ru.otus.homework8.dto.converter.CommentDtoConverter;
import ru.otus.homework8.exception.BookNotFoundException;
import ru.otus.homework8.exception.CommentNotFoundException;

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
    public CommentDto getCommentById(String commentId) {
        return commentDtoConverter.mapToDto(commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(
                        (String.format("Not found comment with commentId:%s", commentId)))));
    }

    @Override
    public List<CommentDto> getCommentsByBookId(String bookId) {
        return getBook(bookId).getComments().stream().map(commentDtoConverter::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void saveNewComment(CommentDto newComment, String bookId) {
        Comment comment = commentDtoConverter.mapToEntity(newComment);
        commentRepository.save(comment);
        Book book = getBook(bookId);
        book.getComments().add(comment);
        bookRepository.save(book);
    }

    @Override
    public void updateComment(CommentDto updatingComment) {
        commentRepository.save(commentDtoConverter.mapToEntity(updatingComment));
    }

    @Override
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
        bookRepository.deleteCommentFromBookByCommentId(commentId);
    }

    private Book getBook(String bookId) {
        return bookRepository.findBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Not found book with bookId:%s", bookId)));
    }
}
