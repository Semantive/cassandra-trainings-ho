package controllers;

import dao.PopularQuestionsDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import views.html.index;

import java.util.List;

@org.springframework.stereotype.Controller
public class PopularQuestionsController extends GenericController {

    @Autowired
    private PopularQuestionsDAO popularQuestionsDAO;

    public Result popular() {
        List<Question> questions = popularQuestionsDAO.getPopularQuestions();
        return popularInternal(questions);
    }

    public Result popularAfter(String questionId) {
        List<Question> questions = popularQuestionsDAO.getPopularQuestions(questionId, Direction.AFTER);
        return popularInternal(questions);
    }

    public Result popularBefore(String questionId) {
        List<Question> questions = popularQuestionsDAO.getPopularQuestions(questionId, Direction.BEFORE);
        return popularInternal(questions);
    }

    private Result popularInternal(List<Question> questions) {
        QuestionList list = QuestionList.fromQuestions(QuestionList.Category.POPULAR, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(routes.PopularQuestionsController.popularAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(routes.PopularQuestionsController.popularBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Popular Questions", getAuthentication(), list));
    }
}
