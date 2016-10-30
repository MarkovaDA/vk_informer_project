package com.websystique.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser; 
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.websystique.springsecurity.dto.CourseDTO;
import com.websystique.springsecurity.dto.GroupDTO;
import com.websystique.springsecurity.dto.StudentDTO;
import com.websystique.springsecurity.model.Filter;
import com.websystique.springsecurity.model.Student;
import com.websystique.springsecurity.model.MessageObject;
import com.websystique.springsecurity.service.CourseService;
import com.websystique.springsecurity.service.FacultyService;
import com.websystique.springsecurity.service.GroupService;
import com.websystique.springsecurity.service.StudentService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    
    @Autowired
    private StudentService studentService;
    
    @RequestMapping(value = { "/get_courses" }, method = RequestMethod.GET)
    public List<CourseDTO> getCourses(@RequestParam("fac_id")Integer facId){
        return courseService.getCoursesByFacultyId(facId);
    }
    
    @RequestMapping(value = { "/get_groups" }, method = RequestMethod.GET)
    public List<GroupDTO> getGroups(@RequestParam("course_id")Integer courseId){
        return groupService.getGroupsByCourseId(courseId);
    }
    private final String USER_AGENT = "Mozilla/5.0";

    
    
    @RequestMapping(value = { "/add_students" }, method = RequestMethod.GET)
    public String getMembers(){

        StringBuffer response = null;
    
        try {
            String url = "https://api.vk.com/method/groups.getMembers?group_id=csf2013&count=1000&offset=0&fields=first_name,last_name";
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", USER_AGENT);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String inputLine;
		response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();    
                int commaIndex = response.toString().indexOf("[");
                String responseStr = (response.toString()).substring(commaIndex, response.length() - 2);
                Type listType = new TypeToken<List<Student>>() {}.getType();

                List<Student> students = new Gson().fromJson(responseStr, listType);          
                studentService.saveMultipleStudents(students);
            }            
        } 
        catch (IOException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.toString();
    } 
    
    
    @RequestMapping(value = { "/send_info" }, 
                method = RequestMethod.POST
                //produces = MediaType.APPLICATION_JSON_VALUE,
                //consumes = MediaType.APPLICATION_JSON_VALUE
    )
    //@ResponseStatus(value=HttpStatus.OK)
    @ResponseBody
    public void sendInfoToPeople(/*@RequestBody MessageObject obj*/ @RequestBody Filter[] filters){
      
        /*for(Filter filter:filters)
        {
            if (filter.getGroup() != null){
                studentService.getStudentsByGroupId(filter.getGroup());
            }
            else if (filter.getCourse() !=  null){
                courseService.getUidsByCourse(filter.getCourse());
            }
            else if (filter.getFaculty() != null){
                facultyService.getUidsByFaculty(filter.getFaculty());
            }  
        }*/
        int a = 1;
    }
}
