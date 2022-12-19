package ru.otus.homework14.dao;

import com.github.cloudyrock.spring.v5.EnableMongock;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework14.domain.Book;
import ru.otus.homework14.mongock.changelog.TestLibraryChangelog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@EnableMongock
public class BookRepositoryTest {

    private static final long INITIAL_LIBRARY_SIZE = 2L;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void shouldFindBookById() {
        Query query = new Query().addCriteria(Criteria.where("id").is(TestLibraryChangelog.BOOK_1.getId()));
        Book expectedBook = mongoTemplate.findOne(query, Book.class);
        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(TestLibraryChangelog.BOOK_1);
    }

    @Test
    void shouldFindAllBooks() {
        assertEquals(INITIAL_LIBRARY_SIZE, bookRepository.findAll().size());
    }

    @Test
    void shouldDeleteCommentFromBook() {
        val commentsBeforeDeleting = bookRepository.findById(TestLibraryChangelog.BOOK_1.getId()).get()
                .getComments();
        assertEquals(1, commentsBeforeDeleting.size());
        bookRepository.deleteCommentFromBookByCommentId(TestLibraryChangelog.COMMENTS.get(0).getId());
        val commentsAfterDeleting = bookRepository.findById(TestLibraryChangelog.BOOK_1.getId()).get()
                .getComments();
        assertThat(commentsAfterDeleting).isEmpty();
    }

}
