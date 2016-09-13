package com.websystique.springsecurity.model;

import java.sql.Date;
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

@Table(name="courses")
@Entity
public class Course {
    //id, foundation_date, number, faculty_id
    @Id 
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    
    @Column(name="foundation_date")
    private Date  foundationDate;
    
    @Column(name="number")
    private Integer number;
    
    /*@Column(name="faculty_id")
    private Integer faculty_id;*/
    @ManyToOne()
    @JoinColumn(name="faculty_id")
    private Faculty faculty;
    
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<Group> groups;

    public  Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
    
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
       
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    /*public Integer getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(Integer faculty_id) {
        this.faculty_id = faculty_id;
    }*/   
}
