
package com.websystique.springsecurity.dao;


import com.websystique.springsecurity.model.Settings;
import com.websystique.springsecurity.model.Student;
import com.websystique.springsecurity.model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class StudentDao extends AbstractDao<Integer, Student>{
    //метод использовался на стадии сборки базы данных - добавление множества записей
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
    
   
    //выбрать всех студентов по группе
    public List<Student> getStudentsByGroupId(Integer groupId, Boolean onlyCaptain){
        /*List<Student> students = getSession().createCriteria(Student.class)
                .add(Restrictions.eq("group.id", groupId))
                .add(Restrictions.eq("isCaptain", onlyCaptain)).
                setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();*/
        
        Criteria сriteria = getSession().createCriteria(Student.class)
                 .add(Restrictions.eq("group.id", groupId));
        //только старосту выбираем
        if (onlyCaptain){
            сriteria.add(Restrictions.eq("isCaptain", onlyCaptain));
        }
        return сriteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();
    }
    
    //выбрать старосту группы
    public Student getCaptainOfGroup(Integer groupId){
         return (Student) getSession().createCriteria(Student.class)
                .add(Restrictions.eq("group.id", groupId))
                .add(Restrictions.eq("is_captain", true))
                .uniqueResult();
    }
    
    public Student getStudentByUId(String uid){
        return (Student) getSession().createCriteria(Student.class)
                .add(Restrictions.eq("uid", uid))
                .uniqueResult();
    }
    
    //применить настройки (cмена пароля,почты,флагов оповещений)
    public void applyStudentSettings(Settings settings){        
        //применить остальные настройки
        Student student = getStudentByUId(settings.getLogin());
        
        if (student != null) {
            if (settings.getMail() != null && !settings.getMail().equals(student.getMail()))
                student.setMail(settings.getMail());
            if (settings.getBy_mail() != null)
                student.setByMail(settings.getBy_mail());
            if (settings.getBy_vk() != null)
                student.setByVK(settings.getBy_vk());  
            update(student);
        }       
    }
    
}
