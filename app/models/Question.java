package models;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private List<Answer> answers = new ArrayList<Answer>();

    @Transient
    private User author;

    @Transient
    private int voteCount;

    @Transient
    private int answerCount;

    @Transient
    private int viewCount;

    @Transient
    private boolean answered;

    @Transient
    private boolean follow;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Transient
    public Date getDate() {
        if(getId() != null) {
            return new Date(UUIDs.unixTimestamp(id));
        }
        return null;
    }

    @Transient
    @Override
    public String getAuthorDisplayName() {
        if(author != null) {
            return author.getDisplayName();
        }
        return authorLogin;
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
