package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.QuestionsDao;
import ru.otus.homework.domain.Question;

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
        return dao.getAllQuestions();
    }
}
