package controllers;

import dao.UserDAO;
import models.Credentials;
import models.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import views.html.login;
import views.html.register;

@org.springframework.stereotype.Controller
public class LoginController extends GenericController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RecentQuestionsController recentQuestionsController;

    public Result displayLoginPage() {
        session().put(REFERER, request().getHeader(REFERER));
        return ok(login.render(Form.form(Credentials.class), getAuthentication()));
    }

    public Result displayRegistrationPage() {
        session().put(REFERER, request().getHeader(REFERER));
        return ok(register.render(Form.form(NewUser.class), getAuthentication()));
    }

    public Result login() {
        DynamicForm df = DynamicForm.form().bindFromRequest(request());
        if (df.get("_cancel") != null)
            return redirectBackToReferer();

        Form<Credentials> form = Form.form(Credentials.class).bind(df.data());
        if (form.hasErrors()) {
            form.reject("You must provide both login and password.");
            return badRequest(login.render(form, getAuthentication()));
        }

        Credentials credentials = form.get();
        if (!userDAO.authenticate(credentials)) {
            form.reject("Invalid login or password.");
            return badRequest(login.render(form, getAuthentication()));
        }

        session().put("currentUser", credentials.getLogin());
        return recentQuestionsController.recent();
    }

    public Result register() {
        DynamicForm df = DynamicForm.form().bindFromRequest(request());
        if (df.get("_cancel") != null)
            return redirectBackToReferer();

        Form<NewUser> form = Form.form(NewUser.class).bind(df.data());
        if (form.hasErrors())
            return badRequest(register.render(form, getAuthentication()));

        NewUser user = form.get();
        if (!userDAO.createUser(user)) {
            form.reject("login", "User already exists.");
            return badRequest(register.render(form, getAuthentication()));
        }

        session().put("currentUser", user.getLogin());
        return login();
    }

    public Result logout() {
        session().remove("currentUser");
        return recentQuestionsController.recent();
    }

    public Result redirectBackToReferer() {
        String referer = session().get(REFERER);
        if (referer == null)
            referer = request().getHeader(REFERER);
        if (referer == null)
            referer = "/";
        session().remove(REFERER);
        return redirect(referer);
    }

}
