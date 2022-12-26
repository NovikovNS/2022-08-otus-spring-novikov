package ru.otus.homework14.dao;


import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.homework14.domain.nosql.BookNoSql;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public BookRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void deleteCommentFromBookByCommentId(String commentId) {
        val query = Query.query(Criteria.where("$id").is(commentId));
        val update = new Update().pull("commentNoSql", query);
        mongoTemplate.updateMulti(new Query(), update, BookNoSql.class);
    }

    @Override
    public BookNoSql findBookByCommentId(String commentId) {
        val query = Query.query(Criteria.where("commentNoSql.$id").is(commentId));
        return mongoTemplate.findOne(query, BookNoSql.class);
    }
}
