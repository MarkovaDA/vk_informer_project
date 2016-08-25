package com.websystique.springsecurity.dao;
import com.mongodb.gridfs.GridFSDBFile;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;


public class MongoDBDao {
    
    @Autowired
    protected GridFsTemplate gridFsTemplate;
    
    public void saveImage(InputStream inputStrea, String fileName){
        this.gridFsTemplate.store(inputStrea, fileName);       
    }
    
    public GridFSDBFile getImage(String fileName){
        return 
           this.gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(fileName)));
    }
    
}
