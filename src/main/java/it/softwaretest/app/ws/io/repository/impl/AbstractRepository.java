package it.softwaretest.app.ws.io.repository.impl;

import it.softwaretest.app.ws.utilities.HibernateCreateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractRepository {

    protected Session session;

    public void openConnection() {
        SessionFactory sessionFactory = HibernateCreateSession.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void closeConnection() {
        if (session != null)
            session.close();
    }

}
