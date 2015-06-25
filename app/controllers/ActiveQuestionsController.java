package controllers;

import dao.ActiveQuestionsDAO;
import models.ACtiveQuestion;
import models.Direction;
import models.QuestionList;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import views.html.index;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class ActiveQuestionsController extends GenericController {

    @Autowired
    private ActiveQuestionsDAO activeQuestionsDAO;

    public Result active() {
        List<ACtiveQuestion> questions = getList(activeQuestionsDAO.findAll());
        return activeInternal(questions);
    }

    private List<ACtiveQuestion> getList(Iterable<ACtiveQuestion> iterable) {
        List<ACtiveQuestion> list = new ArrayList<>();
        for (ACtiveQuestion el: iterable) {
            list.add(el);
        }
        return list;
    }

    public Result activeAfter(String questionId) {
        List<ACtiveQuestion> questions = activeQuestionsDAO.getActiveQuestions(questionId, Direction.AFTER.name());
        return activeInternal(questions);
    }

    public Result activeBefore(String questionId) {
        List<ACtiveQuestion> questions = activeQuestionsDAO.getActiveQuestions(questionId, Direction.BEFORE.name());
        return activeInternal(questions);
    }

    private Result activeInternal(List<ACtiveQuestion> questions) {
        QuestionList list = QuestionList.fromActiveQuestions(QuestionList.Category.ACTIVE, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(routes.ActiveQuestionsController.activeAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(routes.ActiveQuestionsController.activeBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Active Questions", getAuthentication(), list));
    }
}
