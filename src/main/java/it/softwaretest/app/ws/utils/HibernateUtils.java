package it.softwaretest.app.ws.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println("Initial Session Factory creation failed! " + e);
            throw new ExceptionInInitializerError(e);
        }

    }

    public static SessionFactory getSessionFactory() {
            return sessionFactory;
    }
}
