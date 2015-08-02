package dao.spring;

import models.spring.ActiveQuestion;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;

import java.util.List;
import java.util.UUID;

public interface ActiveQuestionsDAO extends TypedIdCassandraRepository<ActiveQuestion, UUID> {

    //TODO Implement Spring Data-based mapping and query
    @Query("SELECT * FROM example")
    List<ActiveQuestion> getActiveQuestions(UUID questionId, String direction);
}
