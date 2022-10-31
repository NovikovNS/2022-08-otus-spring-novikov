package ru.otus.homework8.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @DBRef
    @Field(name = "author")
    private Author author;

    @DBRef
    @Field(name = "style")
    private Style style;

    @Field(name = "comments")
    @DBRef(lazy = true)
    private List<Comment> comments = new ArrayList<>();
}
