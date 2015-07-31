package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Collections;
import java.util.List;

import static org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification.createKeyspace;

/**
 * @author Piotr Jędruszuk (pjedruszuk@semantive.com)
 */
@Configuration
@ComponentScan({"dao", "controllers"})
@PropertySource(value = {"classpath:cassandra.properties"})
@EnableCassandraRepositories(basePackages = {"dao"})
public class SpringConfig extends AbstractCassandraConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getKeyspaceName() {
        return env.getProperty("cassandra.keyspace");
    }

    @Override
    protected String getContactPoints() {
        return env.getProperty("cassandra.contactpoints");
    }

    @Override
    protected int getPort() {
        return Integer.parseInt(env.getProperty("cassandra.port"));
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(env.getProperty("cassandra.schema-action"));
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(createKeyspace().name(getKeyspaceName()).withSimpleReplication().ifNotExists());
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {
                "models.spring"
        };
    }
}
