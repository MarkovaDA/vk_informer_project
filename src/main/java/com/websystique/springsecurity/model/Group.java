package com.websystique.springsecurity.model;

//академическая группа

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="groups")
public class Group {
   //id, spec_id, course_id, number
    @Id 
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    
    @Column(name="spec_id")
    private Integer specId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course; 

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }         
    /*@Column(name="course_id")
    private Integer courseId;*/
    
    @Column(name="number")
    private Integer number;
    
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    /*public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }*/

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
}
