package com.warehouse.server;

import com.warehouse.shared.entity.Base;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


/**
 * Created by Дима on 25.04.2017.
 *
 */

public class Hibernate implements Database
{
   private static final SessionFactory factory = new Configuration()
           .configure()
           .buildSessionFactory();

   @Override
   public List<? extends Base> selectSessionByKey(Base example) {
      return null;
   }

   @Override
   public List<? extends Base> selectAllMenuItems() {
      return null;
   }

   @Override
   public List<? extends Base> selectAllUserType() {
      return null;
   }
}
