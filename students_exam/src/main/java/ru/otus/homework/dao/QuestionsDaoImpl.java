package ru.otus.homework.dao;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import ru.otus.homework.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionsDaoImpl implements QuestionsDao {
    private static final int QUESTION_ID_NUMBER = 0;
    private static final int QUESTION_VALUE_NUMBER = 1;
    private static final int QUESTION_ANSWERS_NUMBER = 2;

    private final String fileNameWithQuestions;

    public QuestionsDaoImpl(String fileNameWithQuestions) {
        this.fileNameWithQuestions = fileNameWithQuestions;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        InputStream isStream = getClass().getClassLoader().getResourceAsStream(fileNameWithQuestions);

        if (isStream != null) {
            InputStreamReader isr = new InputStreamReader(isStream);
            CSVReader reader = new CSVReaderBuilder(isr).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();
            try {
                List<String[]> data = reader.readAll();
                for (String[] row : data) {
                    List<String> answerList = Arrays.stream(Arrays.copyOfRange(row, QUESTION_ANSWERS_NUMBER, row.length)).collect(Collectors.toList());
                    questions.add(Question
                            .builder()
                            .id(row[QUESTION_ID_NUMBER])
                            .value(row[QUESTION_VALUE_NUMBER])
                            .answerList(answerList)
                            .build());
                }
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }
}
