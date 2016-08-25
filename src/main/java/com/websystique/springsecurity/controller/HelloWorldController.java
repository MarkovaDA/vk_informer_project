package com.websystique.springsecurity.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.websystique.springsecurity.dao.MongoDBDao;
import com.websystique.springsecurity.model.Image;
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
import com.websystique.springsecurity.mongodao.MongoDao;
import com.websystique.springsecurity.service.ImageService;
import com.websystique.springsecurity.service.IssueService;
import com.websystique.springsecurity.service.PetService;
import com.websystique.springsecurity.service.UserProfileService;
import com.websystique.springsecurity.service.UserService;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private UserService userService;
	
        @Autowired
        private IssueService issueService;
        
        @Autowired
        private PetService petService;
        
        @Autowired
        private MongoDao mongoDao;
        
        @Autowired
        private ImageService imageService;
        
        @RequestMapping(value = "/mongo", method = RequestMethod.GET)
        public String mongoTest() throws FileNotFoundException {
            
            /*List<Pet> pets = petService.getPetsByOwner(1);
            String[] images = {"cat1.jpg", "rabbit1.jpg", "sova.jpg", "alligator.jpg", "svinka.jpg", "dog.jpg", "cat2.jpg", "myha.jpg", "krokodile.jpg"};
            InputStream inputStream;
            String id;
            Image petImage;
            int index = -1;
            for(String image: images){
                inputStream = new FileInputStream("/home/darya/pet_clinic_images/" + image); 
                id = mongoDao.store(inputStream, image, "image/jpg", null);
                petImage = new Image();
                petImage.setImageId(id);
                petImage.setPetId(++index);
                imageService.updateImage(petImage);            
            }
            DBObject metaData = new BasicDBObject();
            metaData.put("brand", "Audi");
            metaData.put("model", "Audi A3");
            metaData.put("description","Audi german automobile manufacturer that designs, engineers, and distributes automobiles");*/                                    
            return "welcome";
        }
        //http://stackoverflow.com/questions/22682566/spring-display-image-in-jsp-from-mongodb
        
        @RequestMapping(value="/get_photo", method = RequestMethod.GET)
        public @ResponseBody void getPhotoByPhotoId(HttpServletRequest request, HttpServletResponse response){
            GridFSDBFile image = mongoDao.getById("57bf07b434526d101c094b02");
          
            InputStream is = image.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            int length = (int)image.getLength();
            byte[] data = new byte[(int)image.getLength()];
            try {
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                byte[] imagenEnBytes = buffer.toByteArray();
                response.setHeader("Accept-ranges","bytes");
                response.setContentType(image.getContentType());
                response.setContentLength(imagenEnBytes.length);
                response.setHeader("Expires","0");
                response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Content-Description","File Transfer");
                response.setHeader("Content-Transfer-Encoding:","binary");
                OutputStream out = response.getOutputStream();
                out.write(imagenEnBytes);
                out.flush();
                out.close();
            } 
            catch (IOException ex) {
                Logger.getLogger(HelloWorldController.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        @ResponseBody
        @RequestMapping("/get_photo2")
        public byte[] getPhoto() throws IOException {
            GridFSDBFile image = mongoDao.getById("57bf07b434526d101c094b02");
            InputStream in = image.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            int length = (int)image.getLength();
            byte[] data = new byte[length];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] imagenEnBytes = buffer.toByteArray();
            return imagenEnBytes;
        }
                
        
                
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
                //return "index";
	}
       
	@RequestMapping(value = { "/admin"}, method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}
        
        @RequestMapping(value={"/client"}, method=RequestMethod.GET)
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
        
        @RequestMapping(value={"/employee"}, method=RequestMethod.GET)
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