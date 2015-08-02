package controllers;

import dao.AskedByQuestionsDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import views.html.index;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Controller
public class AskedByMeQuestionsController extends GenericController {

    @Autowired
    private AskedByQuestionsDAO askedByQuestionsDAO;

    public Result askedByMe() {
        String userId = getCurrentUserId();
        List<Question> questions = askedByQuestionsDAO.getAskedByQuestions(userId);
        return askedbyInternal(questions);
    }

    public Result askedByMeAfter(UUID questionId) {
        String userId = getCurrentUserId();
        List<Question> questions = askedByQuestionsDAO.getAskedByQuestions(userId, questionId, Direction.AFTER);
        return askedbyInternal(questions);
    }

    public Result askedByMeBefore(UUID questionId) {
        String userId = getCurrentUserId();
        List<Question> questions = askedByQuestionsDAO.getAskedByQuestions(userId, questionId, Direction.BEFORE);
        return askedbyInternal(questions);
    }

    private Result askedbyInternal(List<Question> questions) {
        QuestionList list = QuestionList.fromQuestions(QuestionList.Category.ASKED_BY_ME, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(controllers.routes.AskedByMeQuestionsController.askedByMeAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(controllers.routes.AskedByMeQuestionsController.askedByMeBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Questions Asked By Me", getAuthentication(), list));
    }
}
