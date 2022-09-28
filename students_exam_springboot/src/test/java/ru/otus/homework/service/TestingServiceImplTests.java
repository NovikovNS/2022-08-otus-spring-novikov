package ru.otus.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.Testing;

import java.util.List;

import static org.mockito.Mockito.mock;

class TestingServiceImplTests {

    private TestingServiceImpl testingService;
    private IOservice iOservice;
    private final int RIGHT_ANSWER = 2;
    private final List<String> answersList = List.of("Answer1", "Answer2", "Answer3", "Answer4");
    private final List<Question> questionsList = List.of(
            Question.builder().id("1").value("Question1").answerList(answersList).rightAnswer(RIGHT_ANSWER).build());
    private final Student testStudent = Student.builder().name("Nikita").build();

    @BeforeEach
    void setUp() {
        iOservice = mock(IOservice.class);
        MessageService messageService = mock(MessageService.class);
        AppConfig appConfig = mock(AppConfig.class);
        testingService = new TestingServiceImpl(iOservice, messageService, appConfig);
    }

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