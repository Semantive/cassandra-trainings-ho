package controllers;

import models.Question;
import play.data.validation.Constraints;

public class NewQuestionData {
    @Constraints.Required
    private String title;

    @Constraints.Required
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question toQuestion(String authorLogin) {
        Question q = new Question();
        q.setTitle(title);
        q.setText(text);
        q.setAuthorLogin(authorLogin);
        return q;
    }
}
