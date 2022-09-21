package ru.otus.homework.config;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Builder
@Component
@Getter
public class AppConfig {
    private final String csvFileName;
    private final int minRightAnswers;

    public AppConfig(@Value("${csvFileName}") String csvFileName, @Value("${minRightAnswers}") int minRightAnswers) {
        this.csvFileName = csvFileName;
        this.minRightAnswers = minRightAnswers;
    }

}
