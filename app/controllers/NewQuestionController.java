package controllers;

import dao.QuestionDAO;
import dao.UserDAO;
import models.Question;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import views.html.newquestion;
import views.html.register;

import javax.inject.Inject;
import java.util.UUID;

public class NewQuestionController extends GenericController {

    private final QuestionDAO questionDAO;

    private final Form<NewQuestionData> formQuestion;
    private QuestionController questionController;

    @Inject
    public NewQuestionController(FormFactory formFactory, UserDAO userDAO,
                                 QuestionDAO questionDAO, QuestionController questionController) {
        super(userDAO);
        this.formQuestion = formFactory.form(NewQuestionData.class);
        this.questionDAO = questionDAO;
        this.questionController = questionController;
    }

    public Result newQuestionForm() {
        return ok(newquestion.render(formQuestion, getAuthentication()));
    }

    public Result saveQuestion() {
        Form<NewQuestionData> form = formQuestion.bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(newquestion.render(form, getAuthentication()));
        }

        NewQuestionData newQuestionData = form.get();
        Question question = newQuestionData.toQuestion(getCurrentUser().getLogin());
        UUID questionId = questionDAO.createNewQuestion(question);

        return questionController.display(questionId);
    }

}


