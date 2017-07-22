package models;

import com.datastax.driver.core.utils.UUIDs;

import java.util.Date;
import java.util.UUID;

public class ActiveQuestion implements ListableQuestion {

    private Integer year;
    private Integer month;
    private UUID lastUpdated;

    private UUID id;
    private String authorLogin;
    private String title;
    private String text;
    private User author;
    private int voteCount = 0;
    private int answerCount = 0;
    private int viewCount = 0;
    private boolean answered;
    private boolean follow;

    public ActiveQuestion() {
    }

    public ActiveQuestion(Integer yer, Integer month, UUID lastUpdated, UUID id, String authorLogin, String title, String text) {
        this.year = year;
        this.month = month;
        this.lastUpdated = lastUpdated;
        this.id = id;
        this.authorLogin = authorLogin;
        this.title = title;
        this.text = text;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public UUID getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(UUID lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthorLogin() {
        if (author != null) {
            return author.getLogin();
        }
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    @Override
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String getAuthorDisplayName() {
        return getAuthor().getDisplayName();
    }

    @Override
    public Date getDate() {
        if (getId() != null) {
            return new Date(UUIDs.unixTimestamp(id));
        }
        return null;
    }

    @Override
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    @Override
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
