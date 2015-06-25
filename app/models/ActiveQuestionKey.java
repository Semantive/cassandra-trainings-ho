package models;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Piotr Jędruszuk
 */
@PrimaryKeyClass
public class ActiveQuestionKey implements Serializable {

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String questionId;

    @PrimaryKeyColumn(name = "lastUpdated", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date lastUpdated;

    public ActiveQuestionKey(String questionId, Date lastUpdated) {
        this.questionId = questionId;
        this.lastUpdated = lastUpdated;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
