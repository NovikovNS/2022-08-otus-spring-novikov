package ru.otus.homework.service;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Student;

@Component
public class StudentServiceImpl implements StudentService {
    private final IOservice ioService;
    private final MessageService messageService;

    public StudentServiceImpl(IOservice ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }

    @Override
    public Student getStudentDataFromConsole() {
        ioService.outputString(messageService.getMessage("person.hello_name"));
        var studentName = ioService.readString();
        ioService.outputString(messageService.getMessage("person.surname"));
        var studentSurname = ioService.readString();

        return Student.builder().name(studentName).surname(studentSurname).build();
    }

}
