package ru.otus.homework.service;

import com.opencsv.CSVReader;

public interface Reader {

    CSVReader csvReaderFromResource(String fileName);
}
