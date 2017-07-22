package controllers;

import dao.UserDAO;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import views.html.login;
import views.html.register;

import javax.inject.Inject;

public class LoginController extends GenericController {
    private Logger.ALogger logger = play.Logger.of(getClass());

    private final RecentQuestionsController recentQuestionsController;

    private final FormFactory formFactory;
    private final Form<LoginData> formUserLoginData;
    private final Form<LoginRegisterData> formUserRegisterData;

    @Inject
    public LoginController(FormFactory formFactory,
                           UserDAO userDAO,
                           RecentQuestionsController recentQuestionsController) {
        super(userDAO);
        this.formFactory = formFactory;
        this.formUserLoginData = formFactory.form(LoginData.class);
        this.formUserRegisterData = formFactory.form(LoginRegisterData.class);
        this.recentQuestionsController = recentQuestionsController;
    }

    public Result displayLoginPage() {
        request().header(REFERER).ifPresent(s -> session().put(REFERER, s));
        return ok(login.render(formUserLoginData, getAuthentication()));
    }

    public Result displayRegistrationPage() {
        request().header(REFERER).ifPresent(s -> session().put(REFERER, s));

        return ok(register.render(formUserRegisterData, getAuthentication()));
    }

    public Result login() {
        DynamicForm df = formFactory.form().bindFromRequest();

        if (df.get("_cancel") != null)
            return redirectBackToReferer();

        Form<LoginData> form = formUserLoginData.bind(df.rawData());
        if (form.hasErrors()) {
            logger.debug("errors = {}", form.allErrors());
            form = form.withGlobalError("You must provide both login and password.");
            return badRequest(login.render(form, getAuthentication()));
        }

        LoginData loginData = form.get();
        logger.debug("login {}, password {}", loginData.getLogin(), loginData.getPassword());
        if (!userDAO.authenticate(loginData.getLogin(), loginData.getPassword())) {
            form = form.withGlobalError("Invalid login or password.");
            return badRequest(login.render(form, getAuthentication()));
        }

        session().put("currentUser", loginData.getLogin());
        return recentQuestionsController.recent();
    }

    public Result register() {
        DynamicForm df = formFactory.form().bindFromRequest();
        if (df.get("_cancel") != null)
            return redirectBackToReferer();

        Form<LoginRegisterData> form = formUserRegisterData.bindFromRequest();
        if (form.hasErrors())
            return badRequest(register.render(form, getAuthentication()));

        LoginRegisterData userData = form.get();
        if (!userDAO.createUser(userData.toUser())) {
            form = form.withError("login", "User already exists.");
            return badRequest(register.render(form, getAuthentication()));
        }

        session().put("currentUser", userData.getLogin());
        return login();
    }

    public Result logout() {
        session().remove("currentUser");
        return recentQuestionsController.recent();
    }

    private Result redirectBackToReferer() {
        String referer = session().get(REFERER);
        if (referer == null)
            referer = request().header(REFERER).orElse("/");
        session().remove(REFERER);
        return redirect(referer);
    }

}
