package controllers;

import dao.QuestionDAO;
import models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.mvc.Result;
import views.html.newquestion;

import java.util.Date;

@org.springframework.stereotype.Controller
public class NewQuestionController extends GenericController {

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private QuestionController questionController;

    public Result newQuestionForm() {
        Form<Question> form = new Form<>(Question.class);
        return ok(newquestion.render(form, getAuthentication()));
    }

    public Result saveQuestion() {
        Form<Question> form = new Form<>(Question.class).bindFromRequest(request());
        Question question = form.get();
        question.setDate(new Date());;
        question.setAuthor(getCurrentUser());

        questionDAO.createNewQuestion(question);

        return questionController.display(question.getId());
    }

}


