package ru.otus.homework14.domain.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework14.dao.BookRepository;
import ru.otus.homework14.domain.jpa.CommentSql;
import ru.otus.homework14.domain.nosql.CommentNoSql;

@Component
@AllArgsConstructor
public class CommentMapper implements JpaMapper<CommentNoSql, CommentSql>{

    private final BookRepository bookRepository;

    @Override
    public CommentSql mapToSql(CommentNoSql commentNoSql) {
        return CommentSql.builder()
                .id(Long.parseLong(commentNoSql.getId()))
                .comment(commentNoSql.getComment())
                .bookId(Long.parseLong((bookRepository.findBookByCommentId(commentNoSql.getId()).getId())))
                .build();
    }
}