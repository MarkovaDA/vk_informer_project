/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websystique.springsecurity.service;
import com.websystique.springsecurity.dao.CourseDao;
import com.websystique.springsecurity.dto.CourseDTO;
import com.websystique.springsecurity.dto.FacultyDTO;
import com.websystique.springsecurity.model.Course;
import java.util.ArrayList;
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
    
    public List<CourseDTO> getCoursesByFacultyId(int id){
        List<Course> courses = dao.getCoursesByFacultyId(id);
        List<CourseDTO> coursesDTO = new ArrayList<>();
        courses.forEach(item -> {coursesDTO.add(new CourseDTO(item)); });
        return coursesDTO;       
    }
}
