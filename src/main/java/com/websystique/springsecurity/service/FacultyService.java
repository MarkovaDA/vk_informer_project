
package com.websystique.springsecurity.service;

import com.websystique.springsecurity.dao.FacultyDao;
import com.websystique.springsecurity.dto.FacultyDTO;
import com.websystique.springsecurity.model.Faculty;
import com.websystique.springsecurity.model.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("facultyService")
@Transactional
public class FacultyService {
    
    @Autowired
    private FacultyDao dao;
    
    public FacultyDTO getFacultyById(int id){
        return new FacultyDTO(dao.getFacultyById(id));
    }
    
    public List<FacultyDTO> getFaculties(){
        List<Faculty> faculties = dao.getFaculties();
        List<FacultyDTO> facultiesDto = new ArrayList<>();
        faculties.forEach(item -> {facultiesDto.add(new FacultyDTO(item));});
        return facultiesDto;
    }
    
    public List<Student> getUidsByFacultyId(int facultyId){
        return dao.getStudentsFromFaculty(facultyId);
    }
}
