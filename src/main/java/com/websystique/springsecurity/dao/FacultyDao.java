
package com.websystique.springsecurity.dao;


import com.websystique.springsecurity.model.Course;
import com.websystique.springsecurity.model.Faculty;
import com.websystique.springsecurity.model.Group;
import com.websystique.springsecurity.model.Student;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    //все студенты факультета
    public List<String> getStudentsFromFaculty(int facultyId){
        Faculty faculty =  (Faculty)getSession().createCriteria(Faculty.class)
                .add(Restrictions.eq("id", facultyId))
                .uniqueResult();
        Set<Student> students = new HashSet();
        Set<Course> cources = faculty.getCourses();//все курсы факультета
        Set<Group> groupByCources = new HashSet<>();
        List<String> uids = new ArrayList<>();
        for(Course course: cources){
            groupByCources = course.getGroups();            
            for(Group group: groupByCources){
                students.addAll(group.getStudents());
            }
        }
        for(Student selectedStudent: students){
           uids.add(selectedStudent.getUid());
        }
        return uids;
    }
    
}
