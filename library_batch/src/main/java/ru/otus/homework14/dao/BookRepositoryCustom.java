package ru.otus.homework14.dao;

import ru.otus.homework14.domain.nosql.BookNoSql;

public interface BookRepositoryCustom {
    void deleteCommentFromBookByCommentId(String commentId);
    BookNoSql findBookByCommentId(String commentId);
}
