package controllers;

import dao.UnansweredQuestionsDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import views.html.index;

import java.util.List;

@org.springframework.stereotype.Controller
public class UnansweredQuestionsController extends GenericController {

    @Autowired
    private UnansweredQuestionsDAO unansweredQuestionsDAO;

    public Result unanswered() {
        List<Question> questions = unansweredQuestionsDAO.getUnansweredQuestions();
        return unansweredInternal(questions);
    }

    public Result unansweredAfter(String questionId) {
        List<Question> questions = unansweredQuestionsDAO.getUnansweredQuestions(questionId, Direction.AFTER);
        return unansweredInternal(questions);
    }

    public Result unansweredBefore(String questionId) {
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
