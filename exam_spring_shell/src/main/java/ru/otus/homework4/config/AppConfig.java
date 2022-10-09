package ru.otus.homework4.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Setter
@ConfigurationProperties(prefix = "application")
public class AppConfig {

    private String csvFileName;
    private String formatFile;
    private int minRightAnswers;
    private Locale locale;

    public String getCsvFileName() {
        return csvFileName + "_" + locale + formatFile;
    }
    public String getFormatFile() {
        return formatFile;
    }
    public int getMinRightAnswers() {
        return minRightAnswers;
    }
    public Locale getLocale() {
        return locale;
    }
}
