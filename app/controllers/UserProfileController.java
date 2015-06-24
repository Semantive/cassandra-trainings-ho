package controllers;

import dao.UserDAO;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.mvc.Result;
import views.html.userprofile;
import views.html.userprofileedit;

@org.springframework.stereotype.Controller
public class UserProfileController extends GenericController {

    @Autowired
    private UserDAO userDAO;

    public Result display(String login) {
        User user = userDAO.getUser(login);
        return ok(userprofile.render(user, getAuthentication()));
    }

    public Result edit() {
        User user = getCurrentUser();
        return ok(userprofileedit.render(Form.form(User.class).fill(user), getAuthentication()));
    }

    public Result saveProfile() {
        Form<User> form = Form.form(User.class).bindFromRequest(request());
        if (form.hasErrors())
            return badRequest(userprofileedit.render(form, getAuthentication()));

        String updateLogin = form.apply("login").valueOr("");
        String currentLogin = getCurrentUserId();
        if (!updateLogin.equals(currentLogin)) {
            form.reject("You are allowed to edit only your own profile.");
            return badRequest(userprofileedit.render(form, getAuthentication()));
        }

        if (!userDAO.updateUser(form.get())) {
            form.reject("User cannot be saved.");
            return badRequest(userprofileedit.render(form, getAuthentication()));
        }

        return ok(userprofile.render(getCurrentUser(), getAuthentication()));
    }

}
