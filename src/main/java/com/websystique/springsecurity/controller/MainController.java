package com.websystique.springsecurity.controller;

import com.websystique.springsecurity.dto.CourseDTO;
import com.websystique.springsecurity.dto.FacultyDTO;
import com.websystique.springsecurity.model.Course;
import com.websystique.springsecurity.model.Faculty;
import com.websystique.springsecurity.model.Group;
import com.websystique.springsecurity.model.SessionUser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springsecurity.model.User;
import com.websystique.springsecurity.model.UserProfile;
import com.websystique.springsecurity.service.CourseService;
import com.websystique.springsecurity.service.FacultyService;
import com.websystique.springsecurity.service.GroupService;
import com.websystique.springsecurity.service.StudentService;
import com.websystique.springsecurity.service.UserProfileService;
import com.websystique.springsecurity.service.UserService;
import java.util.Iterator;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private UserService userService;
        
        @Autowired
        private FacultyService facultyService;
        
        @Autowired
        private CourseService courseService;
        
        @Autowired
        private GroupService groupService;
        
        @Autowired
        private StudentService studentService;
	
        @RequestMapping(value={"/test_service/","/test_service"}, method=RequestMethod.GET)
        public void testService(){
            List<String> list1 = facultyService.getUidsByFacultyId(1);
            List<String> list2 = courseService.getUidsByCourseId(1);
            List<String> list3 = studentService.getUidsByGroupId(8);
        }
        
            
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
                List<CourseDTO> facultets  = courseService.getCoursesByFacultyId(1);
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
	}
       
	@RequestMapping(value = { "/admin"}, method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}
        
        @RequestMapping(value = { "/user"}, method = RequestMethod.GET)
	public String userPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
                model.addAttribute("faculties", facultyService.getFaculties());
		return "user";
	}
        
        @RequestMapping(value = { "/adresat"}, method = RequestMethod.GET)
	public String adresatPage(ModelMap model) {
                //пароль zhirafic
                //логин - id вконтакте
                //пароль - присылается на почту сразу после добавления админом пользователя
                //пароль можно сменить в настройках
		model.addAttribute("user", getPrincipal());
		return "adresat";
	}
      
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "newuser";
	}
        
        //vechicles.client
        //accounts.comments
        
        
        
	/*
	 * This method will be called on form submission, handling POST request It
	 * also validates the user input
	 */
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String saveRegistration(@Valid User user,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("There are errors");
			return "newuser";
		}
		userService.save(user);
		
		System.out.println("First Name : "+user.getFirstName());
		System.out.println("Last Name : "+user.getLastName());
		System.out.println("SSO ID : "+user.getLogin());
		System.out.println("Password : "+user.getPassword());
		System.out.println("Email : "+user.getEmail());
		System.out.println("Checking UsrProfiles....");
		if(user.getUserProfiles()!=null){
			for(UserProfile profile : user.getUserProfiles()){
				System.out.println("Profile : "+ profile.getType());
			}
		}
		
		model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
		return "registrationsuccess";
	}
        
        //общая ссылка входа
	@RequestMapping(value={"/entrance/","/entrance"}, method=RequestMethod.GET)
        public ModelAndView welcomeAll()
        {
            User current = getCurrentUser();
            SessionUser.setCurrentUser(current);
            Set<UserProfile> roles = current.getUserProfiles();
            Iterator<UserProfile> iterator = roles.iterator();     
            if (roles.size() >= 1){
                String roleName = iterator.next().getType().toLowerCase(); //смотрим по первому типу роли
                //на страничку лектора
                if (roleName.endsWith("lect"))
                    return new ModelAndView("redirect:/user");
                //на страничку адресата - опции рассылки сообщений
                if (roleName.endsWith("adresat"))
                    return new ModelAndView("redirect:/adresat");
                //на страничку админа - администрирование сервиса
                if (roleName.endsWith("admin"))
                    return new ModelAndView("redirect:/admin");
            }
            return new ModelAndView("redirect:/");
        }
        
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
        private User getCurrentUser()
	{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof UserDetails) 
	    {
	    	String login = ((UserDetails) principal).getUsername();
	    	User loginUser = userService.findByLogin(login);
	    	return  loginUser;
	    }

	    return null;
	}
	
	
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
        
}