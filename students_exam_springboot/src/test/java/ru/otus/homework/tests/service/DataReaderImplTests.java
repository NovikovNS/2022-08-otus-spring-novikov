package ru.otus.homework.tests.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.exceptions.CSVFailedReadingException;
import ru.otus.homework.service.DataReaderImpl;

import java.io.IOException;

@SpringBootTest(classes = DataReaderImpl.class)
class DataReaderImplTests {

    @Autowired
    private DataReaderImpl reader;

    @Test
    void successedCreatingCSVReaderFromResource() throws IOException, CsvException {
        String correctFileName = "questions.csv";
        CSVReader csvReader = reader.csvReaderFromResource(correctFileName);
        Assertions.assertEquals(3, csvReader.readAll().size());
    }

    @Test
    void failedCreatingCSVReaderFromResource() {
        String incorrectFileName = "not_questions.csv";
        Assertions.assertThrows(CSVFailedReadingException.class, () ->reader.csvReaderFromResource(incorrectFileName));
    }
}