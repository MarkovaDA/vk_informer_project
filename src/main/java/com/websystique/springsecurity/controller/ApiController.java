package com.websystique.springsecurity.controller;

import com.websystique.springsecurity.dto.CourseDTO;
import com.websystique.springsecurity.model.Group;
import com.websystique.springsecurity.service.CourseService;
import com.websystique.springsecurity.service.FacultyService;
import com.websystique.springsecurity.service.GroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private GroupService groupService;
    
    @RequestMapping(value = { "/get_courses" }, method = RequestMethod.GET)
    public List<CourseDTO> getCourses(@RequestParam("fac_id")Integer facId){
        return courseService.getCoursesByFacultyId(facId);
    }
    
    @RequestMapping(value = { "/get_groups" }, method = RequestMethod.GET)
    public List<Group> getGroups(@RequestParam("course_id")Integer courseId){
        //принять в dto, отладить 
        return groupService.getGroupsByCourseId(courseId);
    }
    
}
