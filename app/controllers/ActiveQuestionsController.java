package controllers;

import dao.ActiveQuestionsDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import views.html.index;

import java.util.List;

@org.springframework.stereotype.Controller
public class ActiveQuestionsController extends GenericController {

    @Autowired
    private ActiveQuestionsDAO activeQuestionsDAO;

    public Result active() {
        List<Question> questions = activeQuestionsDAO.getActiveQuestions();
        return activeInternal(questions);
    }

    public Result activeAfter(String questionId) {
        List<Question> questions = activeQuestionsDAO.getActiveQuestions(questionId, Direction.AFTER.name());
        return activeInternal(questions);
    }

    public Result activeBefore(String questionId) {
        List<Question> questions = activeQuestionsDAO.getActiveQuestions(questionId, Direction.BEFORE.name());
        return activeInternal(questions);
    }

    private Result activeInternal(List<Question> questions) {
        QuestionList list = new QuestionList(QuestionList.Category.ACTIVE, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(routes.ActiveQuestionsController.activeAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(routes.ActiveQuestionsController.activeBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Active Questions", getAuthentication(), list));
    }
}
