
package com.websystique.springsecurity.controller;

import com.websystique.springsecurity.model.Pet;
import com.websystique.springsecurity.model.SessionUser;
import com.websystique.springsecurity.service.PetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = { "/client"})
public class ClientController {
     
    @Autowired
    private PetService petService;
    
     @RequestMapping(value = {"/pets"}, method = RequestMethod.GET)
     public String petsPage(){
        return "pets";
     }
     
     @ResponseBody
     @RequestMapping(value={"/get_pets"}, method = RequestMethod.GET)
     public List<Pet> getPets(){
        int userId = SessionUser.getCurrentUser().getId();
        return petService.getPetsByOwner(userId);
     }
        
}
