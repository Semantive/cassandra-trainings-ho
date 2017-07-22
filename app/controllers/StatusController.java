package controllers;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import dao.cassandra.CassandraSupport;
import dao.UserDAO;
import play.mvc.Result;
import views.html.status;

import javax.inject.Inject;

public class StatusController extends GenericController {

    private final CassandraSupport cassandraSupport;

    @Inject
    public StatusController(UserDAO userDAO, CassandraSupport cSupport) {
        super(userDAO);
        this.cassandraSupport = cSupport;
    }

    public Result displayStatus() {
        Cluster cluster = cassandraSupport.getCluster();
        Metadata metadata = cluster.getMetadata();
        return ok(status.render(metadata.getAllHosts(), getAuthentication()));
    }

}
