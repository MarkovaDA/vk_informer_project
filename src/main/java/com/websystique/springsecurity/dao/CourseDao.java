
package com.websystique.springsecurity.dao;


import com.websystique.springsecurity.model.Course;
import com.websystique.springsecurity.model.Group;
import com.websystique.springsecurity.model.Student;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
    
    //все студенты курса
    public List<Student> getStudentsFromCourse(int courseId, boolean onlyCaptain){
        Course course  = (Course)getSession().createCriteria(Course.class)
                .add(Restrictions.eq("id", courseId))
                .uniqueResult();
        List<Student> students = new ArrayList();
        Set<Group> groupByCources = course.getGroups();       
        for(Group group: groupByCources){
            students.addAll(group.getStudents());
        }
        //если нужны только старосты групп
        if (onlyCaptain){
           //фильтруем список,выбирая только старост
            students = students.stream().filter(
                item -> item.getIsCaptain() == true
            ).collect(Collectors.toList());
        }
        return students;
    } 
}
