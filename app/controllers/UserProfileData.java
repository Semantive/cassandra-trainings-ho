package controllers;

import models.User;
import play.data.validation.Constraints;

public class UserProfileData {
    @Constraints.Required(message = "Login is required")
    @Constraints.Pattern(value = "[a-zA-Z0-9_]*", message = "Login should include only alphanumeric characters and underscores")
    @Constraints.MinLength(value = 3, message = "Minimum length is 3")
    private String login;

    @Constraints.Pattern(value = "[a-zA-Z ]*", message = "First name should consists of letters")
    private String firstName;
    @Constraints.Pattern(value = "[a-zA-Z ]*", message = "Last name should consists of letters")
    private String lastName;

    @Constraints.Required(message = "Email is required")
    @Constraints.Email(message = "This should be a valid email address")
    private String email;

    private String password;

    public UserProfileData() {
    }

    public UserProfileData(User user) {
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        User u = new User();
        u.setLogin(login);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setPassword(password);
        return u;
    }
}
