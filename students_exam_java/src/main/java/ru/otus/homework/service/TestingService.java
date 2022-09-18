package ru.otus.homework.service;

import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.Test;

import java.util.List;

public interface TestingService {
    Test createTest(Student student, List<Question> questions);

    void processingTest(Test test);
}
