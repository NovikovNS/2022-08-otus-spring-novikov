package ru.otus.homework.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework.exceptions.CSVFailedReadingException;

import java.io.IOException;

class DataReaderImplTests {

    private DataReaderImpl reader;

    @BeforeEach
    void setUp() {
        reader = new DataReaderImpl();
    }

    @Test
    void successedCreatingCSVReaderFromResource() throws IOException, CsvException {
        String correctFileName = "questions.csv";
        CSVReader csvReader = reader.csvReaderFromResource(correctFileName);
        Assertions.assertEquals(5, csvReader.readAll().size());
    }

    @Test
    void failedCreatingCSVReaderFromResource() {
        String incorrectFileName = "not_questions.csv";
        Assertions.assertThrows(CSVFailedReadingException.class, () ->reader.csvReaderFromResource(incorrectFileName));
    }
}