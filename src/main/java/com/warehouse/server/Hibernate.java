package com.warehouse.server;

import com.warehouse.shared.entity.UserDetail;
import com.warehouse.shared.entity.UserType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Дима on 25.04.2017.
 *
 */

public class Hibernate
{
   private static final SessionFactory factory = new Configuration()
           .configure()
           .buildSessionFactory();

   public static Session openSession()
   {
      return factory.openSession();
   }

}
