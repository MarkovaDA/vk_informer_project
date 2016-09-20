
package com.websystique.springsecurity.dto;

import com.websystique.springsecurity.model.Course;
import java.sql.Date;


public class CourseDTO {
    private Integer id;
    private Date  foundationDate;
    private Integer number;
    
    public CourseDTO(){}
    
   
    
    public CourseDTO(Course course){
        this.id = course.getId();
        this.foundationDate = course.getFoundationDate();
        this.number = course.getNumber();
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
    
    
}
