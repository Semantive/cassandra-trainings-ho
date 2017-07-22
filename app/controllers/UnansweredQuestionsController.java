package controllers;

import dao.UnansweredQuestionsDAO;
import dao.UserDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import play.mvc.Result;
import views.html.index;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class UnansweredQuestionsController extends GenericController {

    private final UnansweredQuestionsDAO unansweredQuestionsDAO;

    @Inject
    public UnansweredQuestionsController(UserDAO userDAO, UnansweredQuestionsDAO unansweredQuestionsDAO) {
        super(userDAO);
        this.unansweredQuestionsDAO = unansweredQuestionsDAO;
    }

    public Result unanswered() {
        List<Question> questions = unansweredQuestionsDAO.getUnansweredQuestions();
        return unansweredInternal(questions);
    }

    public Result unansweredAfter(UUID questionId) {
        List<Question> questions = unansweredQuestionsDAO.getUnansweredQuestions(questionId, Direction.AFTER);
        return unansweredInternal(questions);
    }

    public Result unansweredBefore(UUID questionId) {
        List<Question> questions = unansweredQuestionsDAO.getUnansweredQuestions(questionId, Direction.BEFORE);
        return unansweredInternal(questions);
    }

    private Result unansweredInternal(List<Question> questions) {
        QuestionList list = QuestionList.fromQuestions(QuestionList.Category.UNANSWERED, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(controllers.routes.UnansweredQuestionsController.unansweredAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(controllers.routes.UnansweredQuestionsController.unansweredBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Unanswered Questions", getAuthentication(), list));
    }
}
