package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.Testing;

import java.util.List;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final QuestionService questionService;
    private final TestingService testingService;
    private final StudentService studentService;

    @Autowired
    public ApplicationRunnerImpl(QuestionService questionService, TestingService testingService, StudentService studentService) {
        this.questionService = questionService;
        this.testingService = testingService;
        this.studentService = studentService;
    }

    public void run(){
        Student student = studentService.getStudentDataFromConsole();
        List<Question> questions = questionService.getAllQuestions();
        Testing testing = testingService.createTest(student, questions);
        testingService.processingTest(testing);
    }
}
