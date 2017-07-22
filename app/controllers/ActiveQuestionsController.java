package controllers;

import dao.ActiveQuestionsDAO;
import dao.UserDAO;
import models.ActiveQuestion;
import models.Direction;
import models.QuestionList;
import play.mvc.Result;
import views.html.index;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActiveQuestionsController extends GenericController {

    private final ActiveQuestionsDAO activeQuestionsDAO;

    @Inject
    public ActiveQuestionsController(UserDAO userDAO, ActiveQuestionsDAO activeQuestionsDAO) {
        super(userDAO);
        this.activeQuestionsDAO = activeQuestionsDAO;
    }

    public Result active() {
        List<ActiveQuestion> questions = getList(activeQuestionsDAO.findAll());
        return activeInternal(questions);
    }

    private List<ActiveQuestion> getList(Iterable<ActiveQuestion> iterable) {
        List<ActiveQuestion> list = new ArrayList<>();
        for (ActiveQuestion el : iterable) {
            list.add(el);
        }
        return list;
    }

    public Result activeAfter(UUID questionId) {
        List<ActiveQuestion> questions = activeQuestionsDAO.getActiveQuestions(questionId, Direction.AFTER.name());
        return activeInternal(questions);
    }

    public Result activeBefore(UUID questionId) {
        List<ActiveQuestion> questions = activeQuestionsDAO.getActiveQuestions(questionId, Direction.BEFORE.name());
        return activeInternal(questions);
    }

    private Result activeInternal(List<ActiveQuestion> questions) {
        QuestionList list = QuestionList.fromActiveQuestions(QuestionList.Category.ACTIVE, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(controllers.routes.ActiveQuestionsController.activeAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(controllers.routes.ActiveQuestionsController.activeBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Active Questions", getAuthentication(), list));
    }
}
