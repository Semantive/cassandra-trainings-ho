package dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;

/**
 * Created by Maciej Migacz on 2014-05-05
 * email: maciejmigacz@gmail.com
 */
public class CassandraClient {
    private static CassandraClient ourInstance = new CassandraClient();
    private Cluster cluster;
    private Session session;

    public static CassandraClient getInstance() {
        return ourInstance;
    }

    private CassandraClient() {
        cluster = Cluster.builder()
                .addContactPoint("127.0.0.1")
                .withReconnectionPolicy(new ConstantReconnectionPolicy(200))
                .build();

        createSchema();
        session = cluster.connect("workshop");
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
                "CREATE TABLE IF NOT EXISTS workshop.user ( \n" +
                        "\tlogin text,\n" +
                        "\tfirst_name text,\n" +
                        "\tlast_name text,\n" +
                        "\temail text,\n" +
                        "\tpassword text,\n" +
                        "\treputation int,\n" +
                        "\tPRIMARY KEY (login)\n" +
                        ");"
        );

        s.close();
    }
}
