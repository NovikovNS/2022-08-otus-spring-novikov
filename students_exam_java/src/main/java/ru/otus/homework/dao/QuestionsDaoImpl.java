package ru.otus.homework.dao;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.domain.Question;
import ru.otus.homework.service.QuestionConverter;
import ru.otus.homework.service.DataReader;

import java.util.List;

@Component
public class QuestionsDaoImpl implements QuestionsDao {

    private final QuestionConverter questionConverter;
    private final DataReader dataReader;
    private final AppConfig appConfig;

    @Autowired
    public QuestionsDaoImpl(QuestionConverter questionConverter, DataReader dataReader, AppConfig appConfig) {
        this.questionConverter = questionConverter;
        this.dataReader = dataReader;
        this.appConfig = appConfig;
    }

    @Override
    public List<Question> getAllQuestions() {
        CSVReader csvReader = dataReader.csvReaderFromResource(appConfig.getCsvFileName());
        return questionConverter.getQuestionsFromCVS(csvReader);
    }
}
