package dao;

import models.ActiveQuestion;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.EMPTY_LIST;

public class ActiveQuestionsDAO {
    public List<ActiveQuestion> findAll() {
        return EMPTY_LIST;
    }

    public List<ActiveQuestion> getActiveQuestions(UUID questionId, String direction) {
        return EMPTY_LIST;
    }

}
