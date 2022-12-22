package ru.otus.homework14.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework14.domain.jpa.AuthorSql;
import ru.otus.homework14.domain.jpa.BookSql;
import ru.otus.homework14.domain.jpa.StyleSql;
import ru.otus.homework14.domain.mappers.CommentMapper;
import ru.otus.homework14.domain.nosql.AuthorNoSql;
import ru.otus.homework14.domain.nosql.BookNoSql;
import ru.otus.homework14.domain.nosql.StyleNoSql;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

import static java.util.stream.Collectors.toList;

@Configuration
@AllArgsConstructor
public class JobBookConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoTemplate mongoTemplate;
    private final CommentMapper commentMapper;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public MongoItemReader<AuthorNoSql> authorReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<AuthorNoSql>()
                .name("authorReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(AuthorNoSql.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public ItemProcessor<AuthorNoSql, AuthorSql> authorProcessor() {
        return author -> AuthorSql.builder()
                .id(Long.parseLong(author.getId()))
                .name(author.getName())
                .build();
    }

    @Bean
    public JpaItemWriter<AuthorSql> authorWriter() {
        return new JpaItemWriterBuilder<AuthorSql>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step authorStep() {
        return stepBuilderFactory.get("authorStep")
                .<AuthorNoSql, AuthorSql>chunk(10)
                .reader(authorReader(mongoTemplate))
                .processor(authorProcessor())
                .writer(authorWriter())
                .build();
    }

    @Bean
    public MongoItemReader<StyleNoSql> styleReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<StyleNoSql>()
                .name("authorReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(StyleNoSql.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public ItemProcessor<StyleNoSql, StyleSql> styleProcessor() {
        return style -> StyleSql.builder()
                .id(Long.parseLong(style.getId()))
                .name(style.getName())
                .build();
    }

    @Bean
    public JpaItemWriter<StyleSql> styleWriter() {
        return new JpaItemWriterBuilder<StyleSql>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step styleStep() {
        return stepBuilderFactory.get("styleStep")
                .<StyleNoSql, StyleSql>chunk(10)
                .reader(styleReader(mongoTemplate))
                .processor(styleProcessor())
                .writer(styleWriter())
                .build();
    }

    @Bean
    public MongoItemReader<BookNoSql> bookReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<BookNoSql>()
                .name("authorReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(BookNoSql.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public ItemProcessor<BookNoSql, BookSql> bookProcessor() {
        return book -> BookSql.builder()
                .id(Long.parseLong(book.getId()))
                .name(book.getName())
                .author(AuthorSql.builder()
                        .id(Long.parseLong(book.getAuthor().getId()))
                        .name(book.getAuthor().getName())
                        .build())
                .styleSql(StyleSql.builder()
                        .id(Long.parseLong(book.getStyle().getId()))
                        .name(book.getStyle().getName())
                        .build())
                .commentSqls(book.getComments().stream().map(commentMapper::mapToSql).collect(toList()))
                .build();
    }

    @Bean
    public JpaItemWriter<BookSql> bookWriter() {
        return new JpaItemWriterBuilder<BookSql>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step bookStep() {
        return stepBuilderFactory.get("bookStep")
                .<BookNoSql, BookSql>chunk(10)
                .reader(bookReader(mongoTemplate))
                .processor(bookProcessor())
                .writer(bookWriter())
                .build();
    }

    @Bean
    public Job migrateMongoBookLibraryToJPA() {
        return jobBuilderFactory.get("migrateMongoBookLibraryToJPA")
                .start(authorStep())
                .next(styleStep())
                .next(bookStep())
                .build();
    }
}
