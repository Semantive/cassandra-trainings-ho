package controllers;

import models.User;

/**
 * @author Piotr Jędruszuk (pjedruszuk@semantive.com)
 */
public class Authentication {
    private Boolean authenticated;
    private User currentUser;

    public Authentication(Boolean authenticated, User currentUser) {
        this.authenticated = authenticated;
        this.currentUser = currentUser;
    }

    public Boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
