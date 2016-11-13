
package com.websystique.springsecurity.service;
import com.websystique.springsecurity.dao.CourseDao;
import com.websystique.springsecurity.dto.CourseDTO;
import com.websystique.springsecurity.dto.FacultyDTO;
import com.websystique.springsecurity.model.Course;
import com.websystique.springsecurity.model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author darya
 */
@Service("courseService")
@Transactional
public class CourseService {
    
    @Autowired
    private CourseDao dao;
    
    //получение курсов 
    public List<CourseDTO> getCoursesByFacultyId(int id){
        //отсортировать курсы
        List<Course> courses = dao.getCoursesByFacultyId(id);
        List<CourseDTO> coursesDTO = new ArrayList<>();
        courses.forEach(item -> {coursesDTO.add(new CourseDTO(item)); });
        Collections.sort(coursesDTO);
        return coursesDTO;          
    }
    
    public List<Student> getUidsByCourseId(int courseId, boolean onlyCaptain){
        return dao.getStudentsFromCourse(courseId, onlyCaptain);
    }
}
