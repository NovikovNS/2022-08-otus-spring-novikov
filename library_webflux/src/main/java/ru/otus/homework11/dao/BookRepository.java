package ru.otus.homework11.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework11.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
