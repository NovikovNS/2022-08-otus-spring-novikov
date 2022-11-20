package ru.otus.homework8.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
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
public class TestLibraryChangelog {

    public static final List<Author> AUTHORS = List.of(
            Author.builder().name("Пушкин").build(),
            Author.builder().name("Конан Дойль").build());
    public static final List<Style> STYLES = List.of(
            Style.builder().name("Роман").build(),
            Style.builder().name("Детектив").build());
    public static final List<Comment> COMMENTS = List.of(
            Comment.builder().comment("Неплохая книжка").build(),
            Comment.builder().comment("Не по мне").build());
    public static final Book BOOK_2 = Book.builder().name("Шерлок Холмс").author(AUTHORS.get(1))
            .style(STYLES.get(1)).comments(List.of(COMMENTS.get(1))).build();
    public static final Book BOOK_1 = Book.builder().name("Евгений Онегин").author(AUTHORS.get(0))
            .style(STYLES.get(0)).comments(List.of(COMMENTS.get(0))).build();


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
        styleRepository.save(STYLES.get(0));
        styleRepository.save(STYLES.get(1));
    }


    @ChangeSet(order = "003", id = "initComments", author = "novikov")
    public void initComments(CommentRepository commentRepository) {
        commentRepository.save(COMMENTS.get(0));
        commentRepository.save(COMMENTS.get(1));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "novikov")
    public void initBook(BookRepository bookRepository) {
        bookRepository.save(BOOK_1);
        bookRepository.save(BOOK_2);
    }

}
