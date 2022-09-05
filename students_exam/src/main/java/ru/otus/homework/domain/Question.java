package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Question {

    private String id;

    private String value;

    private List<String> answerList;

    @Override
    public String toString() {
        return "Question:" + value + ", AnswerList=" + answerList;
    }
}
