
package com.websystique.springsecurity.model;


public class Filter {
    
    private Integer faculty;
    private Integer course;
    private Integer group;
    private Boolean only_captain;
    

    public Boolean getOnly_captain() {
        return only_captain;
    }

    public void setOnly_captain(Boolean only_captain) {
        this.only_captain = only_captain;
    }

        
    public Filter(){
    }

    public Filter(Integer faculty, Integer course, Integer group) {
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }
    
    
    public Integer getFaculty() {
        return faculty;
    }

    public void setFaculty(Integer faculty) {
        this.faculty = faculty;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }    
}
