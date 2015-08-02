package models;

import java.util.ArrayList;
import java.util.List;

public class QuestionWithAnswers {
    private Question question;
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Answer> answers = new ArrayList<Answer>();

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
