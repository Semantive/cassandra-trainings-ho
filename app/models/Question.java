package models;

import java.util.Date;

public class Question implements ListableQuestion {

    private String id;

    private User author;

    private String title;
    private String text;
    private Date date;
    private int voteCount = 0;
    private int answerCount = 0;
    private int viewCount = 0;
    private boolean answered;
    private boolean follow;

    public Question() {
    }

    public Question(String id, User author, String title, String text, Date date, int voteCount, int answerCount, int viewCount, boolean answered, boolean follow) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.date = date;
        this.voteCount = voteCount;
        this.answerCount = answerCount;
        this.viewCount = viewCount;
        this.answered = answered;
        this.follow = follow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    @Override
    public String getAuthorLogin() {
        return getAuthor().getLogin();
    }

    @Override
    public String getAuthorDisplayName() {
        return getAuthor().getDisplayName();
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
