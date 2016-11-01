
package com.websystique.springsecurity.service;

import com.websystique.springsecurity.dao.GroupDao;
import com.websystique.springsecurity.dao.StudentDao;
import com.websystique.springsecurity.dto.GroupDTO;
import com.websystique.springsecurity.model.Group;
import com.websystique.springsecurity.model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
@Transactional
public class StudentService {
    
    @Autowired
    private StudentDao dao;
    
    public List<String> getUidsByGroupId(Integer groupId){
        return dao.getStudentsByGroupId(groupId);
    }
    
    public Student getCaptainOfGroup(Integer groupId){
        return dao.getCaptainOfGroup(groupId);
    }
    
    public void saveMultipleStudents(List<Student> students){
        dao.saveMultipleStudents(students);
    }
   
    public void saveMultipleStudents2(List<Student> students){
        dao.saveMultipleStudents2(students);
    }
}
