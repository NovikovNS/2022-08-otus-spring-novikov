package ru.otus.homework4.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Testing {
    private Student student;

    private List<Question> questionsList;

    private int score = 0;
}
