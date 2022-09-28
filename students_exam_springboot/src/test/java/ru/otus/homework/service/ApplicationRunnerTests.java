package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.commandlinerunners.PreparationApp;

@SpringBootTest
public class ApplicationRunnerTests {

    @MockBean
    IOservice ioService;

    @MockBean
    PreparationApp preparationApp;

    @Autowired
    ApplicationRunnerImpl applicationRunner;

    @Test
    public void createTestingTest() {
        Mockito.when(ioService.readString()).thenReturn("Test");
        Mockito.when(ioService.readInt()).thenReturn(1);
//        Mockito.doReturn(1).when(ioService.readInt());
        applicationRunner.run();
    }
}
