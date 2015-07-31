package models;

import models.spring.ActiveQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    public Category getCategory() {
        return category;
    }

    public List<ListableQuestion> getQuestions() {
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
    private final List<ListableQuestion> questions;

    private String forwardLink = null;
    private String backwardLink = null;

    public boolean isCategory(Category buttonCategory) {
        return getCategory().equals(buttonCategory);
    }

    public static QuestionList fromQuestions(Category category, List<Question> questions) {
        List<ListableQuestion> listableQuestions = new ArrayList<>();
        listableQuestions.addAll(questions);
        return new QuestionList(category, listableQuestions);
    }

    public static QuestionList fromActiveQuestions(Category category, List<ActiveQuestion> questions) {
        List<ListableQuestion> listableQuestions = new ArrayList<>();
        listableQuestions.addAll(questions);
        return new QuestionList(category, listableQuestions);
    }

    public QuestionList(Category category, List<ListableQuestion> questions) {
        this.category = category;
        this.questions = questions;
    }


}
