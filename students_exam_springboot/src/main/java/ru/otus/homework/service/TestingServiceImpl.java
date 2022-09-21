package ru.otus.homework.service;

import org.springframework.stereotype.Component;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.Testing;

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
    public Testing createTest(Student student, List<Question> questions) {
        return Testing.builder()
                .student(student)
                .questionsList(questions)
                .build();
    }

    @Override
    public void processingTest(Testing testing) {
        ioService.outputString(messageService.getMessage("test.start"));
        int testScore = testing.getScore();
        for (Question question: testing.getQuestionsList()) {
            displayQuestion(question);
            testScore = processingStudentAnswer(testScore, question);
        }
        testing.setScore(testScore);
        displayResults(testing);
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

    private void displayResults(Testing testing) {
        String conclusions = (testing.getScore() >= appConfig.getMinRightAnswers()) ?
                messageService.getMessage("test.passed") : messageService.getMessage("test.failed");
        ioService.outputString(messageService.getMessage("test.end"));
        ioService.outputString(messageService.getMessage("test.results"));
        ioService.outputString(messageService.getMessage("test.total_questions") + testing.getQuestionsList().size());
        ioService.outputString(messageService.getMessage("test.right_answers") + testing.getScore());
        ioService.outputString(messageService.getMessage(conclusions));
    }

}
