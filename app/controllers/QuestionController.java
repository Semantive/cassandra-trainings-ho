package controllers;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.UserDAO;
import models.Answer;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import views.html.newquestion;
import views.html.question;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

public class QuestionController extends GenericController {

    private final Form<QuestionNewAnswerData> formAnswer;

    private final QuestionDAO questionDAO;
    private final AnswerDAO answerDAO;

    @Inject
    public QuestionController(FormFactory formFactory, UserDAO userDAO,
                              QuestionDAO questionDAO, AnswerDAO answerDAO) {
        super(userDAO);
        this.formAnswer = formFactory.form(QuestionNewAnswerData.class);
        this.questionDAO = questionDAO;
        this.answerDAO = answerDAO;
    }

    public Result display(UUID id) {
        questionDAO.incrementViewsNumber(id);
        return ok(question.render(questionDAO.getQuestion(id), formAnswer, getAuthentication()));
    }

    public Result postAnswer(UUID questionId) {
        if (!isAuthenticated())
            return badRequest("You must be authenticated to post answers");

        final Form<QuestionNewAnswerData> form = formAnswer.bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(question.render(questionDAO.getQuestion(questionId), form, getAuthentication()));
        }

        QuestionNewAnswerData answerData = form.get();
        answerDAO.saveAnswer(questionId, answerData.getText(), getCurrentUser().getLogin());

        return redirect(controllers.routes.QuestionController.display(questionId));
    }

    public Result upvoteQuestion(UUID questionId) {
        questionDAO.incQuestionVotes(questionId);
        return redirect(controllers.routes.QuestionController.display(questionId));
    }

    public Result upvoteAnswer(UUID questionId, String answerId) {
        answerDAO.incAnswerVotes(questionId, answerId);
        return redirect(controllers.routes.QuestionController.display(questionId));
    }

    public Result downvoteQuestion(UUID questionId) {
        questionDAO.decQuestionVotes(questionId);
        return redirect(controllers.routes.QuestionController.display(questionId));
    }

    public Result downvoteAnswer(UUID questionId, String answerId) {
        answerDAO.decAnswerVotes(questionId, answerId);
        return redirect(controllers.routes.QuestionController.display(questionId));
    }

    public Result toggleFollowQuestion(UUID questionId) {
        questionDAO.toggleFollowStatus(getCurrentUserId(), questionId);
        return redirect(controllers.routes.QuestionController.display(questionId));
    }

}


