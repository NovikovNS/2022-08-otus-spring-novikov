package ru.otus.homework.service;

import org.springframework.stereotype.Component;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.Test;

import java.util.Arrays;
import java.util.List;

@Component
public class TestingServiceImpl implements TestingService {
    private final IOservice ioService;
    private final MessageService messageService;
    private final AppConfig appConfig;

    public TestingServiceImpl(IOservice ioService, MessageService messageService, AppConfig appConfig) {
        this.ioService = ioService;
        this.messageService = messageService;
        this.appConfig = appConfig;
    }

    @Override
    public Test createTest(Student student, List<Question> questions) {
        return Test.builder()
                .student(student)
                .questionsList(questions)
                .build();
    }

    @Override
    public void processingTest(Test test) {
        ioService.outputString(messageService.getMessage("test.start"));
        int testScore = test.getScore();
        for (Question question: test.getQuestionsList()) {
            displayQuestion(question);
            testScore = processingStudentAnswer(testScore, question);
        }
        test.setScore(testScore);
        displayResults(test);
    }

    private int processingStudentAnswer(int testScore, Question question) {
        boolean correctIntAnswer = false;
        while (!correctIntAnswer) {
            ioService.outputString(messageService.getMessage("test.write_answer"));
            int studentAnswer = ioService.readInt();
            if (studentAnswer == question.getRightAnswer()) {
                testScore++;
                correctIntAnswer = true;
            } else if (!Arrays.asList(1, 2, 3, 4).contains(studentAnswer)) {
                ioService.outputString(messageService.getMessage("test.enter_error"));
            } else {
                correctIntAnswer = true;
            }
        }
        return testScore;
    }

    private void displayQuestion(Question question) {
        ioService.outputString(messageService.getMessage("test.question") + " " + question.getId());
        ioService.outputString(question.getValue());
        int idAnswer = 1;
        for (String answer: question.getAnswerList()) {
            ioService.outputString(messageService.getMessage("test.answer") + idAnswer);
            ioService.outputString(answer);
            idAnswer++;
        }
    }

    private void displayResults(Test test) {
        String conclusions = (test.getScore() >= appConfig.getMinRightAnswers()) ?
                messageService.getMessage("test.passed") : messageService.getMessage("test.failed");
        ioService.outputString(messageService.getMessage("test.end"));
        ioService.outputString(messageService.getMessage("test.results"));
        ioService.outputString(messageService.getMessage("test.total_questions") + test.getQuestionsList().size());
        ioService.outputString(messageService.getMessage("test.right_answers") + test.getScore());
        ioService.outputString(messageService.getMessage(conclusions));
    }

}
