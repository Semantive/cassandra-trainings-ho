import config.SpringConfig;
import dao.CassandraClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.GlobalSettings;
import play.Logger;



/**
 * Created by Maciej Migacz on 2014-05-05.
 * email: maciejmigacz@gmail.com
 */
public class Global extends GlobalSettings {

    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    @Override
    public void onStart(play.Application app) {
        super.onStart(app);

        ctx.register(SpringConfig.class);
        ctx.refresh();
        ctx.start();

        CassandraClient.getInstance();

        Logger.info("Application has started");
    }

    @Override
    public void onStop(play.Application app) {
        super.onStop(app);

        ctx.close();

        Logger.info("Application shutdown...");
        CassandraClient.getInstance().close();
    }

    @Override
    public <A> A getControllerInstance(Class<A> aClass) {
        return ctx.getBean(aClass);
    }

}
