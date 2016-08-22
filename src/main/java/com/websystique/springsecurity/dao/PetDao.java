
package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.Issue;
import com.websystique.springsecurity.model.Pet;
import com.websystique.springsecurity.model.User;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("petDao")
public class PetDao extends AbstractDao<Integer, Pet>{
    
    public Pet getById(int id){
        return getByKey(id);
    }
    
    public List<Pet> getPetsByOwner(int ownerId){
        return getSession().createCriteria(Pet.class)
                .add(Restrictions.eq("ownerId", ownerId))
                .list();
    }
       
    //как альтернатива этому методу - создать сервис pets_issues и извлеч все pet по issue_id
    public List<Pet> getPetsByIssue(int ownerId, int issueId){
       Predicate<Pet> removeCondition = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
               List<Issue> issues = pet.getIssues();
               
               for(Issue issue : issues){
                   if (issue.getId() == issueId)
                       return false;
               }
               return true;
            }
        };
        List<Pet> allPets = getPetsByOwner(ownerId);
        allPets.removeIf(removeCondition);              
        return allPets;
    }
    
}
