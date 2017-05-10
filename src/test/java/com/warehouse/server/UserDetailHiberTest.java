package com.warehouse.server;

import com.warehouse.server.dao.UserSessionDAO;
import com.warehouse.shared.entity.UserDetail;
import com.warehouse.shared.entity.UserSession;
import com.warehouse.shared.entity.UserType;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Дима on 25.04.2017.
 *
 */

public class UserDetailHiberTest extends TestCase
{
    private SessionFactory factory;

    public void setUp()
    {
        try {
            super.setUp();
            factory = new Configuration()
                    .configure()
                    .addAnnotatedClass(UserDetail.class)
                    .addAnnotatedClass(UserType.class)
                    .buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testInsert()
    {
        assertNotNull(factory);
        boolean inserted;

        Session session = factory.openSession();

        UserType type = new UserType();
        type.setName("Админ");

        UserDetail user = new UserDetail();
        user.setName("Вася");
        user.setPassword("12fdsdfsd37");
        user.setUserType(type);

        session.beginTransaction();
        try {
            session.persist(type);
            session.persist(user);
                                    session.getTransaction().commit();
            inserted = true;
        } catch (Exception  e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            inserted = false;
        } finally {
            session.close();
        }

        assertEquals(true, inserted);

    }

    public void testSelect()
    {
        List<UserSession> sessions;// = new ArrayList<>();
        String query = Resource.getSQLResource(UserSession.FIND_SESSION_BY_KEY);

        try(Session session = factory.openSession()) {
            sessions = session
                    .createNativeQuery(query, UserSession.class)
                    .setParameter("key", "12")
                    .list();
            assertTrue(sessions.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
