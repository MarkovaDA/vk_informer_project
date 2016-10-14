
package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.Group;
import com.websystique.springsecurity.model.Student;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class StudentDao extends AbstractDao<Integer, Student>{
    
    public void saveMultipleStudents(){
        //Session session = sessionFactory.openSession();
        Transaction tx = getSession().beginTransaction();
        Student std1 = new Student();
        std1.setFirstName("darya");
        std1.setLastName("markova");
        std1.setUid("12345");
        Student std2 = new Student();
        std2.setFirstName("nastya");
        std2.setLastName("markova2");
        std2.setUid("54321");
        getSession().save(std1);
        getSession().save(std2);
        tx.commit();
        //session.close();
    }
}
