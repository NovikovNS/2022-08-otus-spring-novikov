package ru.otus.homework.service;

import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.Testing;

import java.util.List;

public interface TestingService {
    Testing createTest(Student student, List<Question> questions);

    void processingTest(Testing testing);
}
