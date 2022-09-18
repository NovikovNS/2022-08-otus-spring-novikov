package ru.otus.homework.dao;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.domain.Question;
import ru.otus.homework.service.QuestionConverter;
import ru.otus.homework.service.Reader;

import java.util.List;

@Component
public class QuestionsDaoImpl implements QuestionsDao {

    private final QuestionConverter questionConverter;
    private final Reader reader;
    private final AppConfig appConfig;

    @Autowired
    public QuestionsDaoImpl(QuestionConverter questionConverter, Reader reader, AppConfig appConfig) {
        this.questionConverter = questionConverter;
        this.reader = reader;
        this.appConfig = appConfig;
    }

    @Override
    public List<Question> getAllQuestions() {
        CSVReader csvReader = reader.csvReaderFromResource(appConfig.getCsvFileName());
        return questionConverter.getQuestionsFromCVS(csvReader);
    }
}
