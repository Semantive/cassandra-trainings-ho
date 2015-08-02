package models.spring;

import com.datastax.driver.core.DataType;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Piotr Jędruszuk
 */
@PrimaryKeyClass
public class ActiveQuestionKey implements Serializable {

    /**
     * Year of last update timestamp
     */
    @PrimaryKeyColumn(name = "year", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer year;

    /**
     * Month of last update timestamp
     */
    @PrimaryKeyColumn(name = "month", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private Integer month;

    /**
     * Last update timestamp
     */
    @PrimaryKeyColumn(name = "lastupdated", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    @CassandraType(type = DataType.Name.TIMEUUID)
    private UUID lastUpdated;

    public ActiveQuestionKey(Integer year, Integer month, UUID lastUpdated) {
        this.year = year;
        this.month = month;
        this.lastUpdated = lastUpdated;
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
}
