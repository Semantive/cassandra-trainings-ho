package dao.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import play.api.Play;

import javax.inject.Singleton;
import java.nio.file.Paths;

@Singleton
public class CassandraSupport {
    private final Cluster cluster;
    private final Session session;

    public CassandraSupport() {
        cluster = Cluster.builder()
                .addContactPoint("127.0.0.1")
                .withReconnectionPolicy(new ConstantReconnectionPolicy(200))
                .build();

        createSchema();
        session = cluster.connect("ho");
    }

    public Cluster getCluster() {
        return cluster;
    }

    public Session getSession() {
        return session;
    }

    public void close() {
        cluster.close();
    }

    private void createSchema() {
        Session s = cluster.connect();

        s.execute(
                "CREATE KEYSPACE IF NOT EXISTS ho\n" +
                        "WITH REPLICATION = {\n" +
                        "\t'class' : 'SimpleStrategy', 'replication_factor' : 1\n" +
                        "}\n" +
                        "AND DURABLE_WRITES = true;"
        );

        s.execute(
                "CREATE TABLE IF NOT EXISTS ho.user ( \n" +
                        "\tlogin text,\n" +
                        "\tfirstname text,\n" +
                        "\tlastname text,\n" +
                        "\temail text,\n" +
                        "\tpassword text,\n" +
                        "\treputation int,\n" +
                        "\tPRIMARY KEY (login)\n" +
                        ");"
        );

        s.execute(
                "create table if not exists ho.question (\n" +
                        "\tid timeuuid,\n" +
                        "\tauthorlogin text,\n" +
                        "\ttitle text,\n" +
                        "\ttext text,\n" +
                        "\tlastupdated timestamp,\n" +
                        "\tprimary key (id)\n" +
                        ");"
        );
        s.close();
    }
}
