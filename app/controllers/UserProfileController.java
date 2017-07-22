package controllers;

import dao.UserDAO;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import views.html.userprofile;
import views.html.userprofileedit;

import javax.inject.Inject;

public class UserProfileController extends GenericController {

    private final Form<UserProfileData> formUser;

    @Inject
    public UserProfileController(FormFactory formFactory, UserDAO userDAO) {
        super(userDAO);
        this.formUser = formFactory.form(UserProfileData.class);
    }

    public Result display(String login) {
        User user = userDAO.getUser(login);
        return ok(userprofile.render(user, getAuthentication()));
    }

    public Result edit() {
        UserProfileData userProfileData = new UserProfileData(getCurrentUser());
        return ok(userprofileedit.render(formUser.fill(userProfileData), getAuthentication()));
    }

    public Result saveProfile() {
        Form<UserProfileData> form = formUser.bindFromRequest();
        if (form.hasErrors())
            return badRequest(userprofileedit.render(form, getAuthentication()));

        String updateLogin = form.apply("login").getValue().orElse("");
        String currentLogin = getCurrentUserId();
        if (!updateLogin.equals(currentLogin)) {
            form.withGlobalError("You are allowed to edit only your own profile.");
            return badRequest(userprofileedit.render(form, getAuthentication()));
        }

        if (!userDAO.updateUser(form.get().toUser())) {
            form.withGlobalError("User cannot be saved.");
            return badRequest(userprofileedit.render(form, getAuthentication()));
        }

        return ok(userprofile.render(getCurrentUser(), getAuthentication()));
    }

}
