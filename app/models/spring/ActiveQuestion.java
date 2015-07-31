package models.spring;

import models.ListableQuestion;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

@Table("active_questions")
public class ActiveQuestion implements ListableQuestion {

    @PrimaryKey
    private ActiveQuestionKey key;

    private String authorLogin;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String text;
    private Date date;
    private int voteCount = 0;
    private int answerCount = 0;
    private int viewCount = 0;
    private boolean answered;
    private boolean follow;

    public ActiveQuestion() {
    }

    public ActiveQuestion(ActiveQuestionKey key, String authorLogin, String authorFirstName, String authorLastName, String title, String text, Date date, int voteCount, int answerCount, int viewCount, boolean answered, boolean follow) {
        this.key = key;
        this.authorLogin = authorLogin;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
        this.text = text;
        this.date = date;
        this.voteCount = voteCount;
        this.answerCount = answerCount;
        this.viewCount = viewCount;
        this.answered = answered;
        this.follow = follow;
    }

    public ActiveQuestionKey getKey() {
        return key;
    }

    public void setKey(ActiveQuestionKey key) {
        this.key = key;
    }

    @Override
    public String getAuthorLogin() {
        return authorLogin;
    }

    @Override
    public String getAuthorDisplayName() {
        if (getAuthorFirstName() != null && getAuthorLastName() != null)
            return getAuthorFirstName() + " " + getAuthorLastName();
        if (getAuthorFirstName() != null)
            return getAuthorFirstName();
        if (getAuthorLastName() != null)
            return getAuthorLastName();

        return getAuthorLogin();
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }


    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    @Override
    public String getId() {
        return key.getLastUpdated().toString();
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }
}
