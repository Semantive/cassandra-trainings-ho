package dao;

import models.ACtiveQuestion;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActiveQuestionsDAO extends TypedIdCassandraRepository<ACtiveQuestion, UUID> {

    //TODO Implement Spring Data-based mapping and query
    @Query("SELECT * FROM example")
    List<ACtiveQuestion> getActiveQuestions(String questionId, String direction);
}
