package ru.otus.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.domain.Question;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionServiceTests {

    @Test
    void testGetAllQuestion() {
        List<String> answersList = Arrays.asList("Many", "10000", "2022", "6666666");
        Question testQuestion = Question.builder()
                .id("4")
                .value("What's the weight of the Sun?")
                .answerList(answersList)
                .build();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        List<Question> testList = questionService.getAllQuestions();

        assertEquals(5, testList.size());
        Assertions.assertTrue(testList.contains(testQuestion));
        context.close();
    }
}
