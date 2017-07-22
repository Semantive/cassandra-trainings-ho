package controllers;

import dao.RecentQuestionsDAO;
import dao.UserDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import play.mvc.Result;
import views.html.index;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class RecentQuestionsController extends GenericController {

    private final RecentQuestionsDAO recentQuestionsDAO;

    @Inject
    public RecentQuestionsController(UserDAO userDAO, RecentQuestionsDAO recentQuestionsDAO) {
        super(userDAO);
        this.recentQuestionsDAO = recentQuestionsDAO;
    }

    public Result recent() {
        List<Question> questions = recentQuestionsDAO.getRecentQuestions();
        return recentInternal(questions);
    }

    public Result recentAfter(UUID questionId) {
        List<Question> questions = recentQuestionsDAO.getRecentQuestions(questionId, Direction.AFTER);
        return recentInternal(questions);
    }

    public Result recentBefore(UUID questionId) {
        List<Question> questions = recentQuestionsDAO.getRecentQuestions(questionId, Direction.AFTER);
        return recentInternal(questions);
    }

    private Result recentInternal(List<Question> questions) {
        QuestionList list = QuestionList.fromQuestions(QuestionList.Category.RECENT, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(controllers.routes.RecentQuestionsController.recentAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(controllers.routes.RecentQuestionsController.recentBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Recent Questions", getAuthentication(), list));
    }
}
