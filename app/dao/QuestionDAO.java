package dao;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import models.Answer;
import models.Question;
import models.QuestionWithAnswers;
import models.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;


@Repository
public class QuestionDAO {

    private Mapper<Question> getQuestionMapper() {
        return new MappingManager(CassandraClient.getInstance().getSession()).mapper(Question.class);
    }

    public void createNewQuestion(Question question) {
        question.setLastUpdated(new Date());
        question.setId(UUIDs.startOf(question.getLastUpdated().getTime()));
        getQuestionMapper().save(question);

        // todo add publishing to ranks
    }

    public void incrementViewsNumber(UUID questionId) {
        // TODO increment the number of view of a given question
    }


    private QuestionWithAnswers mockedQuestion() {
        // TODO Mock code - remove after real implementation
        User user1 = new User("jk");
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");

        User user2 = new User("aragorn");

        QuestionWithAnswers qa = new QuestionWithAnswers();

        Question q = new Question();
        Date now = new Date();
        q.setId(UUIDs.startOf(now.getTime()));
        q.setAuthor(user1);
        q.setTitle("Some question");
        q.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
                "culpa qui officia deserunt mollit anim id est laborum");
        q.setVoteCount(5);

        Answer a = new Answer();
        a.setId("answer-1");
        a.setAuthor(user2);
        a.setText("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, " +
                "totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae " +
                "dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, " +
                "sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam " +
                "est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius " +
                "modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, " +
                "quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea " +
                "commodi consequatur?");
        a.setVoteCount(0);
        a.setDate(new Date(System.currentTimeMillis()));
        qa.getAnswers().add(a);
        qa.setQuestion(q);

        // --------------------------------
        return qa;
    }


    public QuestionWithAnswers getQuestion(UUID questionId) {
        // TODO: Get the question and answers from the database

        Question question = getQuestionMapper().get(questionId);
        if(question == null) {
            // TODO: remove after implementation
            return mockedQuestion();
        }

        User user1 = new User("jk");
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        question.setAuthor(user1);

        // todo add retrieving counters, answers, author etc.
        QuestionWithAnswers qa = new QuestionWithAnswers();
        qa.setQuestion(question);

        return qa;
    }

    public void incQuestionVotes(UUID questionId) {
        // TODO: implement me
    }

    public void decQuestionVotes(UUID questionId) {
        // TODO: implement me
    }

    public void setFollowStatus(String userId, UUID questionId, boolean followState) {
        // TODO: Update follow status in a database

    }
}
