package ru.otus.homework14.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.homework14.dao.AuthorRepository;
import ru.otus.homework14.dao.BookRepository;
import ru.otus.homework14.dao.CommentRepository;
import ru.otus.homework14.dao.StyleRepository;
import ru.otus.homework14.domain.nosql.AuthorNoSql;
import ru.otus.homework14.domain.nosql.BookNoSql;
import ru.otus.homework14.domain.nosql.CommentNoSql;
import ru.otus.homework14.domain.nosql.StyleNoSql;

import java.util.List;

@ChangeLog
public class TestLibraryChangelog {

    public static final List<AuthorNoSql> AUTHORS = List.of(
            AuthorNoSql.builder().id("1").name("Пушкин").build(),
            AuthorNoSql.builder().id("2").name("Конан Дойль").build());
    public static final List<StyleNoSql> STYLE_NO_SQLS = List.of(
            StyleNoSql.builder().id("1").name("Роман").build(),
            StyleNoSql.builder().id("2").name("Детектив").build());
    public static final List<CommentNoSql> COMMENT_NO_SQLS = List.of(
            CommentNoSql.builder().id("1").comment("Неплохая книжка").build(),
            CommentNoSql.builder().id("2").comment("Не по мне").build());
    public static final BookNoSql BOOK_NO_SQL_1 = BookNoSql.builder().id("1").name("Евгений Онегин").author(AUTHORS.get(0))
            .style(STYLE_NO_SQLS.get(0)).comments(List.of(COMMENT_NO_SQLS.get(0))).build();
    public static final BookNoSql BOOK_NO_SQL_2 = BookNoSql.builder().id("2").name("Шерлок Холмс").author(AUTHORS.get(1))
            .style(STYLE_NO_SQLS.get(1)).comments(List.of(COMMENT_NO_SQLS.get(1))).build();

    @ChangeSet(order = "000", id = "dropDB", author = "novikov", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "novikov")
    public void initAuthors(AuthorRepository authorRepository) {
        authorRepository.save(AUTHORS.get(0));
        authorRepository.save(AUTHORS.get(1));
    }

    @ChangeSet(order = "002", id = "initStyles", author = "novikov")
    public void initStyles(StyleRepository styleRepository) {
        styleRepository.save(STYLE_NO_SQLS.get(0));
        styleRepository.save(STYLE_NO_SQLS.get(1));
    }


    @ChangeSet(order = "003", id = "initComments", author = "novikov")
    public void initComments(CommentRepository commentRepository) {
        commentRepository.save(COMMENT_NO_SQLS.get(0));
        commentRepository.save(COMMENT_NO_SQLS.get(1));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "novikov")
    public void initBook(BookRepository bookRepository) {
        bookRepository.save(BOOK_NO_SQL_1);
        bookRepository.save(BOOK_NO_SQL_2);
    }

}
