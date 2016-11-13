
package com.websystique.springsecurity.service;


import com.websystique.springsecurity.dao.StudentDao;
import com.websystique.springsecurity.model.Settings;
import com.websystique.springsecurity.model.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
@Transactional
public class StudentService {
    
    @Autowired
    private StudentDao dao;
    
    public List<Student> getUidsByGroupId(Integer groupId, boolean onlyCaptain){
        return dao.getStudentsByGroupId(groupId, onlyCaptain);
    }
    
    public Student getCaptainOfGroup(Integer groupId){
        return dao.getCaptainOfGroup(groupId);
    }
    
    public void saveMultipleStudents(List<Student> students){
        dao.saveMultipleStudents(students);
    }
   
   
    public Student getStudentInfoByUid(String uid){
        return dao.getStudentByUId(uid);
    }
    
    //применение пользовательских настроек
    public void applyStudentSettings(Settings settings){
        dao.applyStudentSettings(settings);
    }
}
