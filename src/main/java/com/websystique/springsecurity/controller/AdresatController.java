
package com.websystique.springsecurity.controller;

import com.websystique.springsecurity.model.User;
import com.websystique.springsecurity.service.AuthService;
import com.websystique.springsecurity.service.StudentService;
import com.websystique.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/adresat")
public class AdresatController {
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/settings")
    public ModelAndView settingsPage(ModelMap map){
        //uid
        User currentUser = AuthService.getCurrentUser(userService);     
        String mail = studentService.getStudentInfoByUid(currentUser.getLogin()).getMail();
        map.addAttribute("mail", studentService.getStudentInfoByUid(currentUser.getLogin()).getMail());
        return new ModelAndView("adresat_settings");
    }    
}
