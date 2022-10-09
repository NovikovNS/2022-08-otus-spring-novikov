package ru.otus.homework4.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import ru.otus.homework4.domain.Question;
import ru.otus.homework4.exceptions.IncorrectCSVDataForTestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionConverterImpl implements QuestionConverter {
    private static final int QUESTION_ID_NUMBER = 0;
    private static final int QUESTION_VALUE_NUMBER = 1;
    private static final int QUESTION_ANSWERS_START_NUMBER = 2;
    private static final int QUESTION_RIGHT_ANSWER_NUMBER = 6;

    @Override
    public List<Question> getQuestionsFromCVS(CSVReader csvReader) {
        List<Question> questions = new ArrayList<>();

        try {
            List<String[]> data = csvReader.readAll();
            for (String[] row : data) {
                List<String> answerList = Arrays.stream(Arrays.copyOfRange(row, QUESTION_ANSWERS_START_NUMBER, QUESTION_RIGHT_ANSWER_NUMBER))
                        .collect(Collectors.toList());
                questions.add(Question
                        .builder()
                        .id(row[QUESTION_ID_NUMBER])
                        .value(row[QUESTION_VALUE_NUMBER])
                        .answerList(answerList)
                        .rightAnswer(Integer.parseInt(row[QUESTION_RIGHT_ANSWER_NUMBER]))
                        .build());
            }
        } catch (IOException | CsvException e) {
            throw new IncorrectCSVDataForTestException("Error during parsing data from CSV file");
        }

        return questions;
    }
}
