package dao;

import models.Direction;
import models.Question;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.EMPTY_LIST;

@Repository
public class AskedByQuestionsDAO {
    public List<Question> getAskedByQuestions(String userId) {
        return EMPTY_LIST;
    }

    public List<Question> getAskedByQuestions(String userId, UUID questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
