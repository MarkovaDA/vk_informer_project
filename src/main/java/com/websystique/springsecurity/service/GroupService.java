
package com.websystique.springsecurity.service;

import com.websystique.springsecurity.dao.GroupDao;
import com.websystique.springsecurity.model.Group;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("groupService")
@Transactional
public class GroupService {
    
    @Autowired
    private GroupDao dao;
    
    public List<Group> getGroupsByCourseId(int courseId){
        return dao.getGroupsByCourseId(courseId);
    }
    
}
