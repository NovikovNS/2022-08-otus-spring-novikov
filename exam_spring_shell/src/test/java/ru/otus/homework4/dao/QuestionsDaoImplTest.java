package ru.otus.homework4.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework4.config.AppConfig;
import ru.otus.homework4.domain.Question;
import ru.otus.homework4.service.DataReaderImpl;
import ru.otus.homework4.service.QuestionConverterImpl;

import java.util.List;

@SpringBootTest(classes = {QuestionsDaoImpl.class,
        QuestionConverterImpl.class,
        DataReaderImpl.class})
class QuestionsDaoImplTest {

    @MockBean
    AppConfig appConfig;

    @Autowired
    DataReaderImpl dataReader;

    @Autowired
    QuestionConverterImpl questionConverter;

    @Autowired
    QuestionsDaoImpl questionsDao;

    @Test
    void successedGettingAllQuestions() {
        Mockito.when(appConfig.getCsvFileName()).thenReturn("questions.csv");
        List<Question> questions= questionsDao.getAllQuestions();
        Assertions.assertEquals(3, questions.size());
    }

}