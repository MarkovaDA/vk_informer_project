
package com.websystique.springsecurity.controller;

import com.websystique.springsecurity.model.User;
import com.websystique.springsecurity.service.AuthService;
import com.websystique.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/settings")
    public ModelAndView settingsPage(ModelMap map){
        
        User currentUser = AuthService.getCurrentUser(userService);  
        map.addAttribute("user", currentUser.getLogin());
        return new ModelAndView("user_settings");
    }
    
    @PostMapping(value="/change_signature")
    @ResponseBody
    public String changePassword(@RequestParam("signature") String signature){
        User currentUser = AuthService.getCurrentUser(userService);
        if (!currentUser.getSignature().equals(signature))
        userService.changeSignature(currentUser.getLogin(), signature);
        return "success";
    }
}
