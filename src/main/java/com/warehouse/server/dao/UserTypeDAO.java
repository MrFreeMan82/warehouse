package com.warehouse.server.dao;

import com.warehouse.server.Hibernate;
import com.warehouse.shared.entity.Base;
import com.warehouse.shared.entity.UserType;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Дима on 27.04.2017.
 *
 */

public class UserTypeDAO extends DAO
{
    public List<UserType> getAllUserTypes()
    {
     /*   ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            if (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                UserType type = new UserType();
                type.setId(1L);
                type.setName(line.getBytes());

                List<UserType> list = new ArrayList<>();
                list.add(type);
                return list;
            }
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
*/

        try (Session session = Hibernate.openSession())
        {
            System.out.println("Return all types");
            return session.createNamedQuery("all_user_types", UserType.class).list();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            throw e;
        }

    }

    @Override
    public Base findEntity(String namedQuery, Base params) {
        return null;
    }
}
