package dao.cassandra;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    protected void configure() {
        bind(CassandraSupport.class).asEagerSingleton();
    }
}
