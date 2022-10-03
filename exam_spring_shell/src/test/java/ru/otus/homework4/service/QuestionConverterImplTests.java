package ru.otus.homework4.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework4.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = QuestionConverterImpl.class)
class QuestionConverterImplTests {

    @MockBean
    private CSVReader csvReader;

    @Autowired
    private QuestionConverterImpl questionConverter;

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