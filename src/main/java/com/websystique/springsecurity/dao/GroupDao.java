
package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.Faculty;
import com.websystique.springsecurity.model.Group;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupDao extends AbstractDao<Integer, Group>{
    
    public List<Group> getGroupsByCourseId(int courseId){
        return getSession().createCriteria(Group.class)
               .add(Restrictions.eq("course.id", courseId))
               .setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();               
    }
}
