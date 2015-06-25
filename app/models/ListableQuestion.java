package models;

import java.util.Date;

/**
 * @author Piotr Jędruszuk
 */
public interface ListableQuestion {

    String getId();

    String getTitle();

    int getAnswerCount();

    int getVoteCount();

    int getViewCount();

    Date getDate();

    String getAuthorLogin();

    String getAuthorDisplayName();

}
