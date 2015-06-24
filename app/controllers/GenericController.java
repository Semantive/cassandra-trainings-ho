package controllers;

import dao.UserDAO;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Controller;

/**
 * @author Piotr Jędruszuk (pjedruszuk@semantive.com)
 */
public abstract class GenericController extends Controller {

    @Autowired
    private UserDAO userDAO;

    protected boolean isAuthenticated() {
        return session("currentUser") != null;
    }

    protected String getCurrentUserId() {
        return session("currentUser");
    }

    protected User getCurrentUser() {
        String login = session("currentUser");
        if (login == null)
            return null;
        return userDAO.getUser(login);
    }

    protected Authentication getAuthentication() {
        return new Authentication(isAuthenticated(), getCurrentUser());
    }





}
