package com.websystique.springsecurity.dto;

import com.websystique.springsecurity.model.Course;
import com.websystique.springsecurity.model.Faculty;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class FacultyDTO {
    
    private Integer id;
    private String title;
    private Set<CourseDTO> courses = new HashSet<>();
    
    public FacultyDTO(Faculty faculty){
        this.id = faculty.getId();
        this.title = faculty.getTitle();
        //множество курсов
        /*Iterator<Course> courseIterator = faculty.getCourses().iterator();
        Course current;
        
        while(courseIterator.hasNext()){
            current = courseIterator.next();
            courses.add(new CourseDTO(current));
        }*/
        //выборка по id-курса - получаем группы  (сервис курсов)
        //выборка по внешнему ключу факультета - просто получаем список всех курсов (сервис курсов)
        //все эти опреации через сервис курсов
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
     
}
