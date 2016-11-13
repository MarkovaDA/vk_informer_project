package com.websystique.springsecurity.controller;


import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.websystique.springsecurity.dto.CourseDTO;
import com.websystique.springsecurity.dto.GroupDTO;
import com.websystique.springsecurity.model.Filter;
import com.websystique.springsecurity.model.Student;
import com.websystique.springsecurity.model.MessageObject;
import com.websystique.springsecurity.service.AuthService;
import com.websystique.springsecurity.service.CourseService;
import com.websystique.springsecurity.service.FacultyService;
import com.websystique.springsecurity.service.GroupService;
import com.websystique.springsecurity.service.MailService;
import com.websystique.springsecurity.service.StudentService;
import com.websystique.springsecurity.service.UserService;
import com.websystique.springsecurity.service.VKApiService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private UserService userService;
    
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
    //отправка сообщений выбранным людям  
    @PostMapping(value = "/send_info")
    @ResponseBody
    public String sendInfoToPeople(@RequestBody MessageObject obj) throws InterruptedException, IOException{
        
        String signature = AuthService.getCurrentUser(userService).getSignature(); //подпись преподавателя
        //отправляемое сообщение
        String message = obj.getMessage();
        //добавляем подпись, если указано её добавить
        if (obj.getAdd_signature())
        message += System.lineSeparator() +"______________________________" 
            + System.lineSeparator() + signature; 
        
        List<Student> sendors = new ArrayList(); //список получателей сообщений
        //для каждого критерия
        boolean onlyCaptain;
        for(Filter filter:obj.getFilters()){
            onlyCaptain = filter.getOnly_captain(); //отправлять только старосте группы?
            if (filter.getGroup() != null){
                //добавляем в писок получателей список студентов выбранной группы
                sendors.addAll(studentService.getUidsByGroupId(filter.getGroup(), onlyCaptain));
                continue;
            }
            else if (filter.getCourse() != null){
                 //добавляем в писок получателей список студентов выбранного курса
                sendors.addAll(courseService.getUidsByCourseId(filter.getCourse(), onlyCaptain));
                continue;
            }
            else if (filter.getFaculty() != null){
                //добавляем в писок получателей список студентов выбранного факультета
               sendors.addAll(facultyService.getUidsByFacultyId(filter.getFaculty(), onlyCaptain));
               continue;
            }
        }
        //отфильтруем тех, кто настроил себе оповещения по вк
        List<Student> studentsByVk = sendors.stream().filter(
                item -> item.getByVK() == true
        ).collect(Collectors.toList());
        
        for(int i=0; i < studentsByVk.size(); i++){   
            if (studentsByVk.get(i).getUid() == null)
                continue;
            //System.out.println("ПОЛЬЗОВАТЕЛЬ " + studentsByVk.get(i).getFirst_name());           
            VKApiService.sendMessage(studentsByVk.get(i).getUid(), message);                     
            if ((i+1)%3 == 0)
            {                
                Thread.sleep(1000);  //выдерживаем правильно - 3 запроса в секунду             
            }
        }       
        //теперь тех, кто настроил себе отправку сообщений через почту
        List<Student> studentsByMail = sendors.stream().filter(
                item -> item.getByMail() == true
        ).collect(Collectors.toList());        
        mailService.sendMail(message, studentsByMail);                
        return "success_response";
    }
}
