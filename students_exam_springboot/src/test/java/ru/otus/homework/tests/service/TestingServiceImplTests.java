package ru.otus.homework.tests.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.Testing;
import ru.otus.homework.service.IOservice;
import ru.otus.homework.service.MessageService;
import ru.otus.homework.service.TestingService;
import ru.otus.homework.service.TestingServiceImpl;

import java.util.List;

@SpringBootTest(classes = TestingServiceImpl.class)
class TestingServiceImplTests {

    @MockBean
    private IOservice iOservice;

    @MockBean
    private MessageService messageService;

    @MockBean
    private AppConfig appConfig;

    @Autowired
    private TestingService testingService;

    private final int RIGHT_ANSWER = 2;
    private final List<String> answersList = List.of("Answer1", "Answer2", "Answer3", "Answer4");
    private final List<Question> questionsList = List.of(
            Question.builder().id("1").value("Question1").answerList(answersList).rightAnswer(RIGHT_ANSWER).build());
    private final Student testStudent = Student.builder().name("Nikita").build();

    @Test
    void createTest() {
        Testing test = testingService.createTest(testStudent, questionsList);
        Assertions.assertEquals(1, test.getQuestionsList().size());
        Assertions.assertEquals("Nikita", test.getStudent().getName());
        Assertions.assertEquals(0, test.getScore());
    }

    @Test
    void processingTest() {
        Testing test = testingService.createTest(testStudent, questionsList);
        Mockito.when(iOservice.readInt()).thenReturn(RIGHT_ANSWER);

        testingService.processingTest(test);
        Assertions.assertEquals(1, test.getScore());
    }
}