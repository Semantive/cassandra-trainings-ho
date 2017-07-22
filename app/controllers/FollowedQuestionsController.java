package controllers;

import dao.FollowedByQuestionsDAO;
import dao.UserDAO;
import models.Direction;
import models.Question;
import models.QuestionList;
import play.mvc.Result;
import views.html.index;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class FollowedQuestionsController extends GenericController {

    private final FollowedByQuestionsDAO followedByQuestionsDAO;

    @Inject
    public FollowedQuestionsController(UserDAO userDAO, FollowedByQuestionsDAO followedByQuestionsDAO) {
        super(userDAO);
        this.followedByQuestionsDAO = followedByQuestionsDAO;
    }

    public Result followedByMe() {
        String userId = getCurrentUserId();
        List<Question> questions = followedByQuestionsDAO.getFollowedByQuestions(userId);
        return followedInternal(questions);
    }

    public Result followedByMeAfter(UUID questionId) {
        String userId = getCurrentUserId();
        List<Question> questions = followedByQuestionsDAO.getFollowedByQuestions(userId, questionId, Direction.AFTER);
        return followedInternal(questions);
    }

    public Result followedByMeBefore(UUID questionId) {
        String userId = getCurrentUserId();
        List<Question> questions = followedByQuestionsDAO.getFollowedByQuestions(userId, questionId, Direction.BEFORE);
        return followedInternal(questions);
    }

    private Result followedInternal(List<Question> questions) {
        QuestionList list = QuestionList.fromQuestions(QuestionList.Category.FOLLOWED_BY_ME, questions);
        if (!questions.isEmpty()) {
            list.setForwardLink(controllers.routes.FollowedQuestionsController.followedByMeAfter(questions.get(questions.size() - 1).getId()).url());
            list.setBackwardLink(controllers.routes.FollowedQuestionsController.followedByMeBefore(questions.get(questions.size() - 1).getId()).url());
        }
        return ok(index.render("Questions Followed By Me", getAuthentication(), list));
    }
}
