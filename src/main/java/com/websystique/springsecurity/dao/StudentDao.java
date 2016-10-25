
package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.Faculty;
import com.websystique.springsecurity.model.Group;
import com.websystique.springsecurity.model.Student;
import com.websystique.springsecurity.model.Transliteration;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class StudentDao extends AbstractDao<Integer, Student>{
    
    public void saveMultipleStudents(List<Student> students){
        Session session = getSession().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();       
        //Transliteration trans = new Transliteration(); 
        
        for(int i = 0; i < students.size(); i++){
            Student currentStudent = students.get(i);
            currentStudent.setFirst_name("unknown");
            currentStudent.setLast_name("unknown");
            /*String studentFirstName = currentStudent.getFirst_name();
            String studentLastName = currentStudent.getLast_name();
            
            List<String> firstNameArray = new ArrayList();
            List<String> lastNameArray = new ArrayList();
            
            char[] studentFirstNameArr = studentFirstName.toCharArray();
            
            for(int k=0; k < studentFirstNameArr.length; k++){
                firstNameArray.add(Character.toString(studentFirstNameArr[k]));
            }
        
            for(int t=0; t < firstNameArray.size(); t++){
               firstNameArray.set(t, (String)trans.getDictionary().get(firstNameArray.get(t).toLowerCase()));
            }
            
            firstNameArray.forEach(item -> {            
                item = (String)trans.getDictionary().get(item.toLowerCase());
            });
            
            studentFirstName = String.join("", firstNameArray);
            
            char[] studentLastNameArr = studentLastName.toCharArray();
                     
            for(int k=0; k < studentLastNameArr.length; k++){
                lastNameArray.add(Character.toString(studentLastNameArr[k]));
            }           
            lastNameArray.forEach(item -> {
                item = (String)trans.getDictionary().get(item.toLowerCase());
            });
            for(int t=0; t < lastNameArray.size(); t++){
               lastNameArray.set(t, (String)trans.getDictionary().get(lastNameArray.get(t).toLowerCase()));
            }
            
            studentLastName = String.join("", lastNameArray);
            
            currentStudent.setFirst_name(studentFirstName);
            currentStudent.setLast_name(studentLastName);*/
            
            if (!session.contains(currentStudent))
                session.save(currentStudent);       
        }

        tx.commit();
        session.close();
    }
    
    //второй способ сохранения записей - проверка кодировки
    public void saveMultipleStudents2(List<Student> students){
         for(int i = 0; i < students.size(); i++){
           getSession().save(students.get(i));
        }
    }
    
    //выбрать всех студентов по группе
    public List<String> getStudentsByGroupId(Integer groupId){
        List<String> uids = new ArrayList<>();
        List<Student> students = getSession().createCriteria(Student.class)
                .add(Restrictions.eq("group.id", groupId)).
                setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();
        for(Student selectedStudent: students){
           uids.add(selectedStudent.getUid());
        }
        return uids;
    }
    
    //выбрать старосту группу
    public Student getCaptainOfGroup(Integer groupId){
         return (Student) getSession().createCriteria(Student.class)
                .add(Restrictions.eq("group.id", groupId))
                .add(Restrictions.eq("is_captain", true))
                .uniqueResult();
    }
    
    
    
    
}
