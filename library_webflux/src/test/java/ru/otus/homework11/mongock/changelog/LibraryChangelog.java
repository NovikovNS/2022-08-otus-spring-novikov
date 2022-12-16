package ru.otus.homework11.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.homework11.dao.AuthorRepository;
import ru.otus.homework11.dao.BookRepository;
import ru.otus.homework11.dao.StyleRepository;
import ru.otus.homework11.domain.Author;
import ru.otus.homework11.domain.Book;
import ru.otus.homework11.domain.Style;

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

    @ChangeSet(order = "000", id = "dropDB", author = "novikov", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "novikov")
    public void initAuthors(AuthorRepository authorRepository) {
        authorRepository.save(authors.get(0)).block();
        authorRepository.save(authors.get(1)).block();
        authorRepository.save(authors.get(2)).block();
    }

    @ChangeSet(order = "002", id = "initStyles", author = "novikov")
    public void initStyles(StyleRepository styleRepository) {
        styleRepository.save(styles.get(0)).block();
        styleRepository.save(styles.get(1)).block();
        styleRepository.save(styles.get(2)).block();
    }


    @ChangeSet(order = "003", id = "initBooks", author = "novikov")
    public void initBook(BookRepository bookRepository) {
        bookRepository.save(Book.builder().name("Евгений Онегин").author(authors.get(0))
                .style(styles.get(0)).build()).block();
        bookRepository.save(Book.builder().name("Шерлок Холмс").author(authors.get(1))
                .style(styles.get(1)).build()).block();
        bookRepository.save(Book.builder().name("Сияние").author(authors.get(2))
                .style(styles.get(2)).build()).block();
    }

}
