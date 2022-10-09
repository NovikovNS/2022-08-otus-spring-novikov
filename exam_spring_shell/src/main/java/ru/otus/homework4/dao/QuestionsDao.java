package ru.otus.homework4.dao;

import ru.otus.homework4.domain.Question;

import java.util.List;

public interface QuestionsDao {
    List<Question> getAllQuestions();
}
