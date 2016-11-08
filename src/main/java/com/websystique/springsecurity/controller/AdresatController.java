
package com.websystique.springsecurity.controller;

import com.websystique.springsecurity.model.MessageObject;
import com.websystique.springsecurity.model.Settings;
import com.websystique.springsecurity.model.Student;
import com.websystique.springsecurity.model.User;
import com.websystique.springsecurity.service.AuthService;
import com.websystique.springsecurity.service.StudentService;
import com.websystique.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        map.addAttribute("user", currentUser.getLogin());
        map.addAttribute("mail", studentService.getStudentInfoByUid(currentUser.getLogin()).getMail());
        return new ModelAndView("adresat_settings");
    }
    
    
    @PostMapping(value = "/update_settings")
    @ResponseBody
    public String updateSettings(@RequestBody Settings settings){
        //применяем настройки
        studentService.applyStudentSettings(settings);
        //меняем пароль
        if (settings.getNew_password() != null && !userService.changePassword(settings))
            return "cтарый пароль не подтвержден, остальные изменения приняты";
        return "изменения приняты";
    }
}
