package com.websystique.springsecurity.service;

import com.websystique.springsecurity.dao.PetDao;
import com.websystique.springsecurity.model.Pet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("petService")
@Transactional
public class PetService {
    
    @Autowired
    private PetDao dao;
    
    public Pet getById(int id){
        return dao.getById(id);
    }
    
    public List<Pet> getPetsByOwner(int ownerId){
        return dao.getPetsByOwner(ownerId);
    }   
    public List<Pet> getPetsByIssue(int ownerId, int issueId){
        return dao.getPetsByIssue(ownerId, issueId);
    }
    
    public List<Pet> getAllPets(){
        return dao.getAllPets();
    }
    public void updatePet(Pet pet){
        dao.updatePet(pet);
    }
}
