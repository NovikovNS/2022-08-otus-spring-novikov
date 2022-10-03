package ru.otus.homework4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework4.dao.QuestionsDao;
import ru.otus.homework4.domain.Question;
import ru.otus.homework4.exceptions.CSVFailedReadingException;
import ru.otus.homework4.exceptions.IncorrectCSVDataForTestException;

import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {

    private final QuestionsDao dao;

    @Autowired
    public QuestionServiceImpl(QuestionsDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = null;
        try {
            questions = dao.getAllQuestions();
        } catch (CSVFailedReadingException | IncorrectCSVDataForTestException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
