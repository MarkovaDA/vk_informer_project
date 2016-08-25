
package com.websystique.springsecurity.service;
import com.websystique.springsecurity.dao.ImageDao;
import com.websystique.springsecurity.model.Image;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("imageService")
@Transactional
public class ImageService {
    @Autowired
    private ImageDao dao;
    
    public void updateImage(Image img){
        dao.updateImage(img);
    }
    
    public List<Image> getImagesByPetId(Integer petId){
        return dao.getImagesByPetId(petId);
    }
}
