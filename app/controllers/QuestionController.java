package controllers;

import dao.AnswerDAO;
import dao.QuestionDAO;
import models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.mvc.Result;
import views.html.question;

import java.util.Date;

@org.springframework.stereotype.Controller
public class QuestionController extends GenericController {

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private AnswerDAO answerDAO;

    public Result display(String id) {
        Form<Answer> form = new Form<Answer>(Answer.class);
        questionDAO.incrementViewsNumber(id);
        return ok(question.render(questionDAO.getQuestion(id), form, getAuthentication()));
    }

    public Result postAnswer(String id) {
        if (!isAuthenticated())
            return badRequest("You must be authenticated to post answers");

        Form<Answer> answerForm = Form.form(Answer.class).bindFromRequest(request());
        if (!answerForm.hasErrors()) {
            Answer answer = answerForm.get();
            answer.setAuthor(getCurrentUser());
            answer.setDate(new Date());
            answer.setVoteCount(0);
            answerDAO.saveAnswer(id, answer);
        }
        return redirect(routes.QuestionController.display(id));
    }

    public Result upvoteQuestion(String questionId) {
        questionDAO.incQuestionVotes(questionId);
        return redirect(routes.QuestionController.display(questionId));
    }

    public Result upvoteAnswer(String questionId, String answerId) {
        answerDAO.incAnswerVotes(questionId, answerId);
        return redirect(routes.QuestionController.display(questionId));
    }

    public Result downvoteQuestion(String questionId) {
        questionDAO.decQuestionVotes(questionId);
        return redirect(routes.QuestionController.display(questionId));
    }

    public Result downvoteAnswer(String questionId, String answerId) {
        answerDAO.decAnswerVotes(questionId, answerId);
        return redirect(routes.QuestionController.display(questionId));
    }

    public Result followQuestion(String questionId) {
        questionDAO.setFollowStatus(getCurrentUserId(), questionId, true);
        return redirect(routes.QuestionController.display(questionId));
    }

    public Result unfollowQuestion(String questionId) {
        questionDAO.setFollowStatus(getCurrentUserId(), questionId, false);
        return redirect(routes.QuestionController.display(questionId));
    }

}


