package controllers;

import dao.RecentQuestionsDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import views.html.index;

import java.util.List;

@org.springframework.stereotype.Controller
public class RecentQuestionsController extends GenericController {

    @Autowired
    private RecentQuestionsDAO recentQuestionsDAO;

    public Result recent() {
        List<Question> questions = recentQuestionsDAO.getRecentQuestions();
        return recentInternal(questions);
    }

    public Result recentAfter(String questionId) {
        List<Question> questions = recentQuestionsDAO.getRecentQuestions(questionId, Direction.AFTER);
        return recentInternal(questions);
    }

    public Result recentBefore(String questionId) {
        List<Question> questions = recentQuestionsDAO.getRecentQuestions(questionId, Direction.AFTER);
        return recentInternal(questions);
    }

    private Result recentInternal(List<Question> questions) {
        QuestionList list = new QuestionList(QuestionList.Category.RECENT, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(routes.RecentQuestionsController.recentAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(routes.RecentQuestionsController.recentBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Recent Questions", getAuthentication(), list));
    }
}
