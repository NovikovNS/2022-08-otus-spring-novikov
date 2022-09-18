package ru.otus.homework.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class ReaderImpl implements Reader {
    @Override
    public CSVReader csvReaderFromResource(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream != null) {
            InputStreamReader isr = new InputStreamReader(inputStream);
            return new CSVReaderBuilder(isr).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();
        }
        // TODO добавить исключение если inputStream == null
        return null;
    }
}
