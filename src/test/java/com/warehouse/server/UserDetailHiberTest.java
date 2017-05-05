package com.warehouse.server;

import com.warehouse.server.dao.SessionDAO;
import com.warehouse.server.dao.UserTypeDAO;
import com.warehouse.shared.entity.UserDetail;
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
        List<UserDetail> users = new ArrayList<>();

        try (Session session = factory.openSession()) {
            users = session.createNativeQuery(
                    "SELECT * " +
                            "FROM USER_DETAIL ud " +
                            "JOIN USER_TYPE ut ON (ut.ID = ud.TYPE_ID)",
                    UserDetail.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(users.size() > 0);
    }

    public void testNamedQuery()
    {
        com.warehouse.shared.entity.Session template = new com.warehouse.shared.entity.Session();
        template.setKey("12");

        SessionDAO dao = new SessionDAO();

        com.warehouse.shared.entity.Session session = (com.warehouse.shared.entity.Session)
                        dao.querySelect(com.warehouse.shared.entity.Session.FIND_BY_KEY, template);

        assertNotNull(session);
    }
}
