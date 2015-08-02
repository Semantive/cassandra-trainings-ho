package dao;

import com.datastax.driver.core.utils.UUIDs;
import models.Direction;
import models.Question;
import models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;

@Repository
public class RecentQuestionsDAO {
    public List<Question> getRecentQuestions() {
        // TODO Mock code - remove it after implementing DAO
        User user = new User("jk");
        user.setFirstName("Jan");
        user.setLastName("Kowalski");

        Question q1 = new Question();
        q1.setId(UUIDs.endOf(new Date().getTime()));
        q1.setAuthor(user);
        q1.setTitle("First question");
        q1.setVoteCount(5);

        Question q2 = new Question();
        q2.setId(UUIDs.endOf(new Date(System.currentTimeMillis() - 24 * 3600 * 1000).getTime()));
        q2.setAuthor(user);
        q2.setTitle("Second question");
        q2.setVoteCount(3);

        Question q3 = new Question();
        q3.setId(UUIDs.endOf(new Date(System.currentTimeMillis() - 13 * 24 * 3600 * 1000).getTime()));
        q3.setAuthor(user);
        q3.setTitle("Third question");

        List<Question> questions = new ArrayList<Question>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        return questions;
    }

    public List<Question> getRecentQuestions(java.util.UUID questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
