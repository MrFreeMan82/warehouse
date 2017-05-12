package com.warehouse.server;

import com.warehouse.server.dao.LoginDAO;
import com.warehouse.shared.dto.MenuItemDTO;
import com.warehouse.shared.dto.UserDetailDTO;
import com.warehouse.shared.dto.UserTypeDTO;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
                    .addAnnotatedClass(UserDetailDTO.class)
                    .addAnnotatedClass(UserTypeDTO.class)
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

        UserTypeDTO type = new UserTypeDTO();
        type.setName("Админ");

        UserDetailDTO user = new UserDetailDTO();
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
        List<MenuItemDTO> items;// = new ArrayList<>();
        String query = Resource.getSQL(LoginDAO.LOGIN_BY_KEY);

        try(Session session = factory.openSession()) {
            items = session
                   .createNativeQuery(query, MenuItemDTO.class)
                   .list();

            assertTrue(items.size() > 0);
            for(MenuItemDTO item: items)
            {
                System.out.println(item.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
