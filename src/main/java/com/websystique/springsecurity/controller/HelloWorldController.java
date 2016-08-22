package com.websystique.springsecurity.controller;

import com.websystique.springsecurity.model.Issue;
import com.websystique.springsecurity.model.Pet;
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
import com.websystique.springsecurity.service.IssueService;
import com.websystique.springsecurity.service.PetService;
import com.websystique.springsecurity.service.UserProfileService;
import com.websystique.springsecurity.service.UserService;
import java.util.Iterator;
import java.util.Set;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;
	
        @Autowired
        IssueService issueService;
        
        @Autowired
        private PetService petService;
        
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
                //return "index";
	}
       
	@RequestMapping(value = {"/admin/", "/admin"}, method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}
        
        @RequestMapping(value={"/client/","/client"}, method=RequestMethod.GET)
        public ModelAndView welcomeClient(ModelMap model){
            User currentUser = getCurrentUser();
            model.addAttribute("user", currentUser);
            //последнее обращение клиента
            Issue issue = issueService.getLastIssueForClient(currentUser);           
            if (issue != null) {
                model.addAttribute("issue", issue);
                List<Pet> petsByIssue = petService.getPetsByIssue(currentUser.getId(), issue.getId());
                model.addAttribute("pets", petsByIssue);
            }
            return new ModelAndView("client");
        }
        
        @RequestMapping(value={"/employee/","/employee"}, method=RequestMethod.GET)
        public ModelAndView welcomeEmployee(ModelMap model){   
            model.addAttribute("user", getCurrentUser());
            return new ModelAndView("employee");
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
		System.out.println("SSO ID : "+user.getSsoId());
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
           
            if (roles.size() == 1){
                String roleName = iterator.next().getType().toLowerCase();
                if (roleName.endsWith("client"))
                    return new ModelAndView("redirect:/client");
                if (roleName.endsWith("employee"))
                    return new ModelAndView("redirect:/employee");
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
	    	User loginUser = userService.findBySso(login);
	    	return  loginUser;
	    }

	    return null;
	}
	
	
	
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

}