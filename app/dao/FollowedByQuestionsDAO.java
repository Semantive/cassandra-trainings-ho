package dao;

import models.Direction;
import models.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

@Repository
public class FollowedByQuestionsDAO {
    public List<Question> getFollowedByQuestions(String userId) {
        return EMPTY_LIST;
    }

    public List<Question> getFollowedByQuestions(String userId, String questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
