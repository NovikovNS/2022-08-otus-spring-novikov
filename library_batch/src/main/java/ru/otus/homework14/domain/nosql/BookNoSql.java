package ru.otus.homework14.domain.nosql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BookNoSql {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @DBRef
    @Field(name = "author")
    private AuthorNoSql author;

    @DBRef
    @Field(name = "styleNoSql")
    private StyleNoSql style;

    @Field(name = "commentNoSql")
    @DBRef(lazy = true)
    private List<CommentNoSql> comments = new ArrayList<>();
}
