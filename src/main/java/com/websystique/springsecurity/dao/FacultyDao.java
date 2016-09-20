
package com.websystique.springsecurity.dao;


import com.websystique.springsecurity.model.Faculty;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

@Repository("facultyDao")
public class FacultyDao extends AbstractDao<Integer, Faculty>{
    
    public Faculty getFacultyById(int id) {
        return (Faculty)getSession().createCriteria(Faculty.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }
    
    public List<Faculty> getFaculties(){
        return getSession().createCriteria(Faculty.class).
                setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();
    }
}
