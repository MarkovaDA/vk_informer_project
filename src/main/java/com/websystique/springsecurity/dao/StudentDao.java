
package com.websystique.springsecurity.dao;


import com.websystique.springsecurity.model.Student;
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
        
        for(int i = 0; i < students.size(); i++){
            Student currentStudent = students.get(i);
            currentStudent.setFirst_name("unknown");
            currentStudent.setLast_name("unknown");
            
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
    public List<Student> getStudentsByGroupId(Integer groupId){
        List<String> uids = new ArrayList<>();
        List<Student> students = getSession().createCriteria(Student.class)
                .add(Restrictions.eq("group.id", groupId)).
                setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();
        return students;
    }
    
    //выбрать старосту группу
    public Student getCaptainOfGroup(Integer groupId){
         return (Student) getSession().createCriteria(Student.class)
                .add(Restrictions.eq("group.id", groupId))
                .add(Restrictions.eq("is_captain", true))
                .uniqueResult();
    }
    
    public Student getStudentInfoByLogin(String uid){
        return (Student) getSession().createCriteria(Student.class)
                .add(Restrictions.eq("uid", uid))
                .uniqueResult();
    }
    
}
