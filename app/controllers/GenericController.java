package controllers;

import dao.UserDAO;
import models.User;
import play.mvc.Controller;

import javax.inject.Inject;

public abstract class GenericController extends Controller {

    protected final UserDAO userDAO;

    @Inject
    public GenericController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isAuthenticated() {
        return session("currentUser") != null;
    }

    public String getCurrentUserId() {
        return session("currentUser");
    }

    public User getCurrentUser() {
        String login = session("currentUser");
        if (login == null)
            return null;
        return userDAO.getUser(login);
    }

    public Authentication getAuthentication() {
        return new Authentication(isAuthenticated(), getCurrentUser());
    }

}
