package ru.otus.homework.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;
import ru.otus.homework.exceptions.CSVFailedReadingException;

import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class DataReaderImpl implements DataReader {
    @Override
    public CSVReader csvReaderFromResource(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream != null) {
            InputStreamReader isr = new InputStreamReader(inputStream);
            return new CSVReaderBuilder(isr).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();
        } else throw new CSVFailedReadingException("Failed reading CSV file. Check filename or its existence");
    }
}
