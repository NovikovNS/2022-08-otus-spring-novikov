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

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = QuestionsDaoImpl.class)
class QuestionsDaoImplTest {

    @MockBean
    AppConfig appConfig;

    @MockBean
    DataReaderImpl dataReader;

    @MockBean
    QuestionConverterImpl questionConverter;

    @Autowired
    QuestionsDaoImpl questionsDao;

    @Test
    void successedGettingAllQuestions() {
        Mockito.when(questionConverter.getQuestionsFromCVS(any())).thenReturn(List.of(Question.builder().build()));
        List<Question> questions= questionsDao.getAllQuestions();
        Assertions.assertEquals(1, questions.size());
    }

}