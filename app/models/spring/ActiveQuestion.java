package models.spring;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.utils.UUIDs;
import models.ListableQuestion;
import models.User;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

/**
 * Represents recently updated question:
 * <ul>
 *     <li>added</li>
 *     <li>voted</li>
 *     <li>answered</li>
 *     <li>commented</li>
 * </ul>
 *
 * This is a Spring Data Cassandra mapper implementation
 */
@Table("active_questions")
public class ActiveQuestion implements ListableQuestion {

    @PrimaryKey
    private ActiveQuestionKey key;

    @CassandraType(type = DataType.Name.TIMEUUID)
    private UUID id;

    @Column(value = "authorlogin")
    private String authorLogin;

    @Column(value = "title")
    private String title;

    @Column(value = "text")
    private String text;

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


    public ActiveQuestion() {
    }

    public ActiveQuestion(ActiveQuestionKey key, UUID id, String authorLogin, String title, String text) {
        this.key = key;
        this.id = id;
        this.authorLogin = authorLogin;
        this.title = title;
        this.text = text;
    }

    public ActiveQuestionKey getKey() {
        return key;
    }

    public void setKey(ActiveQuestionKey key) {
        this.key = key;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
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
        if(getId() != null) {
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
