package controllers;

import dao.PopularQuestionsDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import views.html.index;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Controller
public class PopularQuestionsController extends GenericController {

    @Autowired
    private PopularQuestionsDAO popularQuestionsDAO;

    public Result popular() {
        List<Question> questions = popularQuestionsDAO.getPopularQuestions();
        return popularInternal(questions);
    }

    public Result popularAfter(UUID questionId) {
        List<Question> questions = popularQuestionsDAO.getPopularQuestions(questionId, Direction.AFTER);
        return popularInternal(questions);
    }

    public Result popularBefore(UUID questionId) {
        List<Question> questions = popularQuestionsDAO.getPopularQuestions(questionId, Direction.BEFORE);
        return popularInternal(questions);
    }

    private Result popularInternal(List<Question> questions) {
        QuestionList list = QuestionList.fromQuestions(QuestionList.Category.POPULAR, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(controllers.routes.PopularQuestionsController.popularAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(controllers.routes.PopularQuestionsController.popularBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Popular Questions", getAuthentication(), list));
    }
}
