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
public class LibraryChangelog {

    private final List<AuthorNoSql> authors = List.of(
            AuthorNoSql.builder().id("1").name("Пушкин").build(),
            AuthorNoSql.builder().id("2").name("Конан Дойль").build(),
            AuthorNoSql.builder().id("3").name("Кинг").build());
    private final List<StyleNoSql> styleNoSqls = List.of(
            StyleNoSql.builder().id("1").name("Роман").build(),
            StyleNoSql.builder().id("2").name("Детектив").build(),
            StyleNoSql.builder().id("3").name("Ужасы").build());
    private final List<CommentNoSql> commentNoSql = List.of(
            CommentNoSql.builder().id("1").comment("Неплохая книжка").build(),
            CommentNoSql.builder().id("2").comment("Не по мне").build(),
            CommentNoSql.builder().id("3").comment("Классная книга").build()
    );

    @ChangeSet(order = "000", id = "dropDB", author = "novikov", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "novikov")
    public void initAuthors(AuthorRepository authorRepository) {
        authorRepository.save(authors.get(0));
        authorRepository.save(authors.get(1));
        authorRepository.save(authors.get(2));
    }

    @ChangeSet(order = "002", id = "initStyles", author = "novikov")
    public void initStyles(StyleRepository styleRepository) {
        styleRepository.save(styleNoSqls.get(0));
        styleRepository.save(styleNoSqls.get(1));
        styleRepository.save(styleNoSqls.get(2));
    }


    @ChangeSet(order = "003", id = "initComments", author = "novikov")
    public void initComments(CommentRepository commentRepository) {
        commentRepository.save(commentNoSql.get(0));
        commentRepository.save(commentNoSql.get(1));
        commentRepository.save(commentNoSql.get(2));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "novikov")
    public void initBook(BookRepository bookRepository) {
        bookRepository.save(BookNoSql.builder().id("1").name("Евгений Онегин").author(authors.get(0))
                .style(styleNoSqls.get(0)).comments(List.of(commentNoSql.get(0))).build());
        bookRepository.save(BookNoSql.builder().id("2").name("Шерлок Холмс").author(authors.get(1))
                .style(styleNoSqls.get(1)).comments(List.of(commentNoSql.get(1))).build());
        bookRepository.save(BookNoSql.builder().id("3").name("Сияние").author(authors.get(2))
                .style(styleNoSqls.get(2)).comments(List.of(commentNoSql.get(2))).build());
    }

}
