package models;

public class User {
    private String login;
    private String firstName;
    private String lastName;
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
        if (getFirstName() != null && !getFirstName().isEmpty() && getLastName() != null && !getLastName().isEmpty())
            return getFirstName() + " " + getLastName();
        if (getLastName() != null && !getLastName().isEmpty())
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
