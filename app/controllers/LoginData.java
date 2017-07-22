package controllers;

import play.data.validation.Constraints;

public class LoginData {
    @Constraints.Required
    private String login;

    @Constraints.Required
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
