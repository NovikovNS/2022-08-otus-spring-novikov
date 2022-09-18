package ru.otus.homework.service;

import com.opencsv.CSVReader;

public interface DataReader {

    CSVReader csvReaderFromResource(String fileName);
}
