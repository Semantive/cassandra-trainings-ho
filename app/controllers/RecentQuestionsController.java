package controllers;

import dao.RecentQuestionsDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import views.html.index;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Controller
public class RecentQuestionsController extends GenericController {

    @Autowired
    private RecentQuestionsDAO recentQuestionsDAO;

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
