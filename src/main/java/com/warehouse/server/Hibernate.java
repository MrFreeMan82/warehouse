package com.warehouse.server;

import com.warehouse.server.entity.CustomEntity;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.dto.ServerException;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;
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

   private Class<? extends DTO> mapToDTO(Class<? extends CustomEntity> clazz) throws java.lang.Exception {

         return clazz.isAnnotationPresent(DTOLocator.class) ?
                 clazz.getAnnotation(DTOLocator.class).value() : ServerException.class;
   }

   static Hibernate getInstance(){return instance;}

   @Override
   public DTO loginByKey(String key) {
      return sessionHashMap.containsKey(key)? sessionHashMap.get(key): new ServerException(INVALID_KEY);
   }

   @Override
   public DTO loginByPassword(String password) throws java.lang.Exception {

      DTO dto = find(new Request()
              .setType(SQL.USER_BY_PASSWORD)
              .setExample(new UserDetail().setPassword(password))
      );

      if (dto instanceof UserDetail) {
         String key = UUID.randomUUID().toString();
         sessionHashMap.put(key, new UserSession((UserDetail) dto, key));
         return sessionHashMap.get(key);
      }
      else if(dto instanceof ServerException) return dto;

      else return new ServerException(INVALID_PASSWORD);
   }

   @Override
   public Long insert(Request request) {
      return internalInsert(request.getExample().createEntity());
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
   public void procedure(Request request) {
      internalProcedureCall(request.getType().name(), SQLMapper.procedureParameters(request));
   }

   @Override
   public DTO find(Request request) throws java.lang.Exception {

      Class<? extends CustomEntity> entityClass = mapToEntity(request.getExample().getClass());
      String sql = SQLMapper.buildFrom(request);
      List<? extends CustomEntity> entities = internalSelect(sql, entityClass);

      return (entities == null) || (entities.size() > 1) ?
              new ServerException("Multiply rows in single request"):
                  mapToDTO(entityClass).newInstance().copyEntity(entities.get(0));
   }

   @Override
   public DTO findList(Request request) throws java.lang.Exception {

      Class<? extends CustomEntity> entityClass = mapToEntity(request.getExample().getClass());
      Class<? extends DTO> dtoClass = mapToDTO(entityClass);
      List<? extends CustomEntity> entities = internalSelect(SQLMapper.buildFrom(request), entityClass);
      Hashed list = new Hashed();
      for(CustomEntity entity: entities) list.addCopy(entity, dtoClass.newInstance());
      return list;
   }

   @Override
   public DTO refresh(Request request) throws java.lang.Exception {
      long id = request.getExample().getId();
      Class<? extends CustomEntity> entityClass = mapToEntity(request.getExample().getClass());
      CustomEntity entity = internalLoad(id, entityClass);
      return entity == null ?
              new ServerException(request.getExample().getClass().getName() + " with id " + id + " not found"):
              mapToDTO(entityClass).newInstance().copyEntity(entity);
   }
}
