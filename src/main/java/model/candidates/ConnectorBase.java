package model.candidates;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConnectorBase implements AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public SessionFactory getSf() {
        return sf;
    }

    private ConnectorBase() {
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    /**
     * Create singleton.
     */
    private static final class Lazy {
        private static final ConnectorBase INST = new ConnectorBase();
    }

    public static ConnectorBase instOf() {
        return ConnectorBase.Lazy.INST;
    }

}
