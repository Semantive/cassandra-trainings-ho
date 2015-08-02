package dao;

import models.Direction;
import models.Question;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.EMPTY_LIST;

@Repository
public class PopularQuestionsDAO {
    public List<Question> getPopularQuestions() {
        return EMPTY_LIST;
    }

    public List<Question> getPopularQuestions(UUID questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
