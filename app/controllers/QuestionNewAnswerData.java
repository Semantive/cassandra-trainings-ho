package controllers;

import play.data.validation.Constraints;

public class QuestionNewAnswerData {

    @Constraints.Required
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
