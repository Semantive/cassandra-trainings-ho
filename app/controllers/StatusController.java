package controllers;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import dao.CassandraClient;
import play.mvc.Result;
import views.html.status;

@org.springframework.stereotype.Controller
public class StatusController extends GenericController {

    public Result displayStatus() {
        Cluster cluster = CassandraClient.getInstance().getCluster();
        Metadata metadata = cluster.getMetadata();
        return ok(status.render(metadata.getAllHosts(), getAuthentication()));
    }

}
