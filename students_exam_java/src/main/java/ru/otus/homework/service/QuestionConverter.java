package ru.otus.homework.service;

import com.opencsv.CSVReader;
import ru.otus.homework.domain.Question;

import java.util.List;

public interface QuestionConverter {
    List<Question> getQuestionsFromCVS(CSVReader csvReader);
}
