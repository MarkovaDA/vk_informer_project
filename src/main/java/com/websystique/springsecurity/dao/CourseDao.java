
package com.websystique.springsecurity.dao;


import com.websystique.springsecurity.model.Course;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

@Repository("courseDao")
public class CourseDao extends AbstractDao<Integer, Course>{
    
    public List<Course> getCoursesByFacultyId(int facultyId){
        return getSession().createCriteria(Course.class)
                .add(Restrictions.eq("faculty.id", facultyId))
                .setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE)
                .list();
    }
    //список групп по id-курса
    //только непонятно - куда добавить 
    //сделать список групп, но в данный моент его не заполнить 
}
