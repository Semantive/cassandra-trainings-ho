package models;

import java.util.List;

public class QuestionList {

    public Category getCategory() {
        return category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getForwardLink() {
        return forwardLink;
    }

    public void setForwardLink(String forwardLink) {
        this.forwardLink = forwardLink;
    }

    public String getBackwardLink() {
        return backwardLink;
    }

    public void setBackwardLink(String backwardLink) {
        this.backwardLink = backwardLink;
    }

    public enum Category { RECENT, POPULAR, ACTIVE, UNANSWERED, ASKED_BY_ME, FOLLOWED_BY_ME }

    private final Category category;
    private final List<Question> questions;

    private String forwardLink = null;
    private String backwardLink = null;

    public boolean isCategory(Category buttonCategory) {
        return getCategory().equals(buttonCategory);
    }

    public QuestionList(Category category, List<Question> questions) {
        this.category = category;
        this.questions = questions;
    }
}
