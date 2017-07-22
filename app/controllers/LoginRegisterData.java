package controllers;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;


@Constraints.Validate
public class LoginRegisterData extends UserProfileData implements Constraints.Validatable<ValidationError> {

    private String password2;

    public LoginRegisterData() {
    }

    @Override
    public ValidationError validate() {
        if (getPassword() == null || getPassword().length() < 8) {
            return new ValidationError("password", "Password is required and should have 8 characters at least");
        }

        if (!getPassword().equals(getPassword2())) {
            return new ValidationError("password2", "Passwords don't match");
        }

        return null;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
