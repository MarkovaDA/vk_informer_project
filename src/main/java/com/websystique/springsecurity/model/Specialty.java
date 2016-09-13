package com.websystique.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

//направление обучения
@Entity
@Table(name="specialties")
public class Specialty {
    //id, code,title, faculty_id
    @Id 
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    
    @NotEmpty
    @Column(name="code", unique=true, nullable=false)
    private String code;
    
    @NotEmpty
    @Column(name="title", nullable=false)
    private String title;
    
    @NotEmpty
    @Column(name="faculty_id", nullable=false)
    private Integer facultyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
    
    
}
