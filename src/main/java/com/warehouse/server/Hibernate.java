package com.warehouse.server;

import com.warehouse.server.entity.CustomEntity;
import com.warehouse.shared.request.Type;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.source.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * Created by Дима on 25.04.2017.
 *
 */

public class Hibernate extends DAO implements DataSource
{
   private static final Hibernate instance = new Hibernate();
   private HashMap<String, UserSession> sessionHashMap = new HashMap<>();
   private static final String INVALID_KEY="invalid key";
   private static final String INVALID_PASSWORD="Invalid password";

   private Hibernate(){}

   private Class<? extends CustomEntity> mapToEntity(Class<? extends DTO> clazz) {

      return  clazz.isAnnotationPresent(EntityLocator.class)?
              clazz.getAnnotation(EntityLocator.class).read(): CustomEntity.class;
   }

   private Class<? extends DTO> mapToDTO(Class<? extends CustomEntity> clazz) throws Exception {

         return clazz.isAnnotationPresent(DTOLocator.class) ?
                 clazz.getAnnotation(DTOLocator.class).value() : Empty.class;
   }

   static Hibernate getInstance(){return instance;}

   @Override
   public DTO loginByKey(String key) {
      return sessionHashMap.containsKey(key)? sessionHashMap.get(key): new Empty(INVALID_KEY);
   }

   @Override
   public DTO loginByPassword(String password) throws Exception {

      DTO dto = find(new Request()
              .setType(Type.USER_BY_PASSWORD)
              .setExample(new UserDetail().setPassword(password))
      );

      if (dto instanceof UserDetail) {
         String key = UUID.randomUUID().toString();
         sessionHashMap.put(key, new UserSession((UserDetail) dto, key));
         return sessionHashMap.get(key);
      }
      else if(dto instanceof Empty) return dto;

      else return new Empty(INVALID_PASSWORD);
   }

   @Override
   public void insert(Request request) {
      internalInsert(request.getExample().createEntity());
   }

   @Override
   public void update(Request request) {
      internalUpdate(request.getExample().createEntity());
   }

   @Override
   public void delete(Request request) {
      internalDelete(request.getExample().createEntity());
   }

   @Override
   public DTO find(Request request) throws Exception {

      Class<? extends CustomEntity> entityClass = mapToEntity(request.getExample().getClass());
      String sql = SQLBuilder.buildFrom(request);
      List<? extends CustomEntity> entities = internalSelect(sql, entityClass);

      return (entities == null) || (entities.size() > 1) ?
              new Empty("Multiply rows in single request"):
                  mapToDTO(entityClass).newInstance().copyEntity(entities.get(0));
   }

   @Override
   public DTO findList(Request request) throws Exception {

      Class<? extends CustomEntity> entityClass = mapToEntity(request.getExample().getClass());
      Class<? extends DTO> dtoClass = mapToDTO(entityClass);
      List<? extends CustomEntity> entities = internalSelect(SQLBuilder.buildFrom(request), entityClass);
      ListDTO list = new ListDTO();
      for(CustomEntity entity: entities) list.addCopy(entity, dtoClass.newInstance());
      return list;
   }
}
