package ru.otus.homework14.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.nosql.BookNoSql;

import java.util.List;

public interface BookRepository extends MongoRepository<BookNoSql, String>, BookRepositoryCustom {
    List<BookNoSql> findAll();
}
