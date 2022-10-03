package ru.otus.homework4.service;

import com.opencsv.CSVReader;

public interface DataReader {

    CSVReader csvReaderFromResource(String fileName);
}
