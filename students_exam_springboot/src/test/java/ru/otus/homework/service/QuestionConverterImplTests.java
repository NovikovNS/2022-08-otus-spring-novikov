package ru.otus.homework.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.homework.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

class QuestionConverterImplTests {

    private QuestionConverterImpl questionConverter;
    private CSVReader csvReader;

    @BeforeEach
    void setUp() {
        questionConverter = new QuestionConverterImpl();
        csvReader = mock(CSVReader.class);
    }

    @Test
    void getQuestionsFromCVS() throws IOException, CsvException {
        String[] testArray = new String[]{"1", "question", "answer1", "answer2", "answer3", "answer4", "3"};
        List<String[]> data = new ArrayList<>();
        data.add(testArray);

        Mockito.when(csvReader.readAll()).thenReturn(data);

        List<Question> result = questionConverter.getQuestionsFromCVS(csvReader);
        Assertions.assertEquals(1, result.size());
    }
}