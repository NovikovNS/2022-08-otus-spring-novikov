package ru.otus.homework4.service;

import ru.otus.homework4.domain.Question;
import ru.otus.homework4.domain.Student;
import ru.otus.homework4.domain.Testing;

import java.util.List;

public interface TestingService {
    Testing createTest(Student student, List<Question> questions);

    void processingTest(Testing testing);
}
