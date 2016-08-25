
package com.websystique.springsecurity.dao;
import com.websystique.springsecurity.model.Image;
import com.websystique.springsecurity.model.Issue;
import com.websystique.springsecurity.model.Pet;
import com.websystique.springsecurity.model.User;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("imageDao")
public class ImageDao extends AbstractDao<Integer, Image>{
    
    public void updateImage(Image img){
        saveOrUpdate(img);
    }
    
    public List<Image> getImagesByPetId(Integer petId){
         return getSession().createCriteria(Image.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("petId", petId))              
                .list()
                ;
    }
    
}
