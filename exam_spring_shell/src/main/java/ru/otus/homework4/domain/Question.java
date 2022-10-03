package ru.otus.homework4.domain;

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

    private int rightAnswer;

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", answerList=" + answerList +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
