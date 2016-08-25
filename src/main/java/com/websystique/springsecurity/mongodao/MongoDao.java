package com.websystique.springsecurity.mongodao;

import org.springframework.stereotype.Repository;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public class MongoDao {
    
    @Autowired
    GridFsTemplate gridFsTemplate;
    
    //сохраняем фотографию
    public String store(InputStream inputStream, String fileName,String contentType, DBObject metaData) {
          return this.gridFsTemplate
          .store(inputStream, fileName, contentType, metaData).getId()
          .toString();
    }
    
    //получить фото по id
    public GridFSDBFile getById(String id) {
        return this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(
          id)));
    }
    
    //получить фото по названию
    public GridFSDBFile getByFilename(String fileName) {
        return gridFsTemplate.findOne(new Query(Criteria.where("filename").is(
        fileName)));
    }
    //получаем все фотографии
    public List findAll() {
        return gridFsTemplate.find(null);
    }
}
