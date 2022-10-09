package ru.otus.homework4.service;

import com.opencsv.CSVReader;
import ru.otus.homework4.domain.Question;

import java.util.List;

public interface QuestionConverter {
    List<Question> getQuestionsFromCVS(CSVReader csvReader);
}
