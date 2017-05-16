package com.warehouse.server;

import com.warehouse.server.dao.DAO;
import com.warehouse.server.dao.DAOLocator;
import com.warehouse.shared.dto.DTO;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Дима on 25.04.2017.
 *
 */

public class Hibernate implements Database
{
   private static Hibernate instance = new Hibernate();

   private Hibernate(){}

   private DAO getDAOByClass(Class cls) throws Exception
   {
      if(!cls.isAnnotationPresent(DAOLocator.class))
         throw new Exception("Unknown Entity " + cls.getName());

      DAOLocator locator = (DAOLocator) cls.getAnnotation(DAOLocator.class);

      return locator.value().newInstance();
   }

   static Hibernate getInstance(){return instance;}

   @Override
   public List<? extends DTO> selectList(String queryName, DTO example) throws Exception {
      return new ArrayList<>();
   }

   @Override
   public DTO selectOne(String queryName, DTO example) throws Exception
   {
      DAO dao = getDAOByClass(example.getClass());

      DAOService.logger.info("DAO is " + dao.getClass().getName());
      @SuppressWarnings("unchecked")
      DTO dto = dao.findOne(queryName, example);

      return  dto;
   }
}
