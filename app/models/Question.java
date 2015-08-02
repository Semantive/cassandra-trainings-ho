package models;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;

import java.util.Date;
import java.util.UUID;

/**
 * Main question reference table, used to store base question data.
 */
@Table(name = "question")
public class Question implements ListableQuestion {

    @PartitionKey
    private UUID id;

    private String authorLogin;
    private String title;
    private String text;
    private Date lastUpdated;

    @Transient
    private User author;

    @Transient
    private int voteCount = 0;

    @Transient
    private int answerCount = 0;

    @Transient
    private int viewCount = 0;

    @Transient
    private boolean answered;

    @Transient
    private boolean follow;

    public Question() {
    }


    public Question(UUID id, String authorLogin, String title, String text, Date lastUpdated) {
        this.id = id;
        this.authorLogin = authorLogin;
        this.title = title;
        this.text = text;
        this.lastUpdated = lastUpdated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getDate() {
        if(getId() != null) {
            return new Date(UUIDs.unixTimestamp(id));
        }
        return null;
    }

    @Override
    public String getAuthorLogin() {
        if(author != null) {
            return author.getLogin();
        }
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    @Override
    public String getAuthorDisplayName() {
        return getAuthor().getDisplayName();
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
