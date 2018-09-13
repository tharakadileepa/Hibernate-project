package lk.udarabattery.newproj.database;

import lk.udarabattery.newproj.entity.Battery;
import lk.udarabattery.newproj.entity.BuyingOrder;
import lk.udarabattery.newproj.entity.BuyingOrderDetail;
import lk.udarabattery.newproj.entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        File hibernateProp = new File("resource/application.properties");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties(hibernateProp)
                .build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Battery.class)
                .addAnnotatedClass(BuyingOrder.class)
                .addAnnotatedClass(BuyingOrderDetail.class)
                .buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
