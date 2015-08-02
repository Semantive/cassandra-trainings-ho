package models;

import java.util.Date;
import java.util.UUID;

/**
 * @author Piotr Jędruszuk
 */
public interface ListableQuestion {

    UUID getId();

    String getTitle();

    int getAnswerCount();

    int getVoteCount();

    int getViewCount();

    Date getDate();

    String getAuthorLogin();

    String getAuthorDisplayName();

}
