package com.websystique.springsecurity.dto;


public class StudentDTO {
    private String uid;
    private String first_name;
    private String last_name;
    private Integer group_id;
    private String mail;
    private Boolean is_captain;

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getIs_captain() {
        return is_captain;
    }

    public void setIs_captain(Boolean is_captain) {
        this.is_captain = is_captain;
    }
    
    

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
