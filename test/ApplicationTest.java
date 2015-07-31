import models.ListableQuestion;
import models.Question;
import models.QuestionList;
import org.junit.Test;
import play.twirl.api.Html;
import views.html.index;

import java.util.ArrayList;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
//        Html html = index.render("test", new QuestionList(QuestionList.Category.ACTIVE, new ArrayList<ListableQuestion>()));
//        assertThat(contentType(html)).isEqualTo("text/html");
//        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }


}
