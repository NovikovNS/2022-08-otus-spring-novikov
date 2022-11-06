package ru.otus.homework8.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework8.dao.AuthorRepository;
import ru.otus.homework8.dao.BookRepository;
import ru.otus.homework8.dao.CommentRepository;
import ru.otus.homework8.dao.StyleRepository;
import ru.otus.homework8.domain.Author;
import ru.otus.homework8.domain.Book;
import ru.otus.homework8.domain.Comment;
import ru.otus.homework8.domain.Style;

import java.util.List;

@ChangeLog
public class LibraryChangelog {

    private final List<Author> authors = List.of(
            Author.builder().name("Пушкин").build(),
            Author.builder().name("Конан Дойль").build(),
            Author.builder().name("Кинг").build());
    private final List<Style> styles = List.of(
            Style.builder().name("Роман").build(),
            Style.builder().name("Детектив").build(),
            Style.builder().name("Ужасы").build());
    private final List<Comment> comments = List.of(
            Comment.builder().comment("Неплохая книжка").build(),
            Comment.builder().comment("Не по мне").build(),
            Comment.builder().comment("Классная книга").build()
    );


    @ChangeSet(order = "000", id = "dropDB", author = "novikov", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "novikov", runAlways = true)
    public void initAuthors(AuthorRepository authorRepository) {
        authorRepository.save(authors.get(0));
    }

    @ChangeSet(order = "002", id = "initStyles", author = "novikov", runAlways = true)
    public void initStyles(StyleRepository styleRepository) {
        styleRepository.save(styles.get(0));
    }


    @ChangeSet(order = "003", id = "initComments", author = "novikov", runAlways = true)
    public void initComments(CommentRepository commentRepository) {
        commentRepository.save(comments.get(0));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "novikov", runAlways = true)
    public void initBook(BookRepository bookRepository) {
        Book book = bookRepository.save(Book.builder()
                .name("Проверка")
                .author(authors.get(0))
                .style(styles.get(0))
                .comments(List.of(comments.get(0))).build());
    }

}
