
package com.websystique.springsecurity.service;

import com.websystique.springsecurity.dao.GroupDao;
import com.websystique.springsecurity.dto.GroupDTO;
import com.websystique.springsecurity.model.Group;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("groupService")
@Transactional
public class GroupService {
    
    @Autowired
    private GroupDao dao;
    
    public List<GroupDTO> getGroupsByCourseId(int courseId){
        //отсортировать группы
        List<Group> groups = dao.getGroupsByCourseId(courseId);
        List<GroupDTO> groupsDTO = new ArrayList<>();
        groups.forEach(item -> {
            GroupDTO groupDTO = new GroupDTO(item.getId(), item.getSpecId(), item.getNumber());
            groupsDTO.add(groupDTO);
        });       
        Collections.sort(groupsDTO);
        return groupsDTO;
    }    
}
