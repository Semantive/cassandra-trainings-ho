package dao;

import models.Answer;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AnswerDAO {
    public void incAnswerVotes(UUID questionId, String answerId) {
        // TODO: implement me
    }

    public void decAnswerVotes(UUID questionId, String answerId) {
        // TODO: implement me
    }

    public void saveAnswer(UUID questionId, Answer answer) {
        // TODO: Add answer to the database

        // --------------------------------
    }
}
