package models;

import play.data.validation.Constraints;

public class User {
    @Constraints.Required(message = "Login is required")
    @Constraints.Pattern(value = "[a-zA-Z0-9_]*", message="Login should include only alphanumeric characters and underscores")
    @Constraints.MinLength(value= 3, message = "Minimum length is 3")
    private String login;

    @Constraints.Pattern(value = "[a-zA-Z ]*", message = "First name should consists of letters")
    private String firstName;
    @Constraints.Pattern(value = "[a-zA-Z ]*", message = "Last name should consists of letters")
    private String lastName;

    @Constraints.Required(message = "Email is required")
    @Constraints.Email(message = "This should be a valid email address")
    private String email;

    private String password;

    private int reputation = 0;

    public User() {
    }

    public User(String login) {
        this.setLogin(login);
    }

    public User(String login, String firstName, String lastName, String email, String password, int reputation) {
        this.setLogin(login);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setReputation(reputation);
    }

    public String getDisplayName() {
        if (getFirstName() != null && getLastName() != null)
            return getFirstName() + " " + getLastName();
        if (getFirstName() != null)
            return getFirstName();
        if (getLastName() != null)
            return getLastName();

        return getLogin();
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

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }
}
