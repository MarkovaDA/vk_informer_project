
package com.websystique.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="students")
public class Student {
    //id, vk_id, first_name,last_name,group_id, mail, is_captain
    @Id 
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    
    @NotEmpty
    @Column(name="uid", unique=true, nullable=false)
    private String uid;
    
    @NotEmpty
    @Column(name="first_name", nullable=false)
    private String first_name;
    
    @NotEmpty
    @Column(name="last_name", nullable=false)
    private String last_name;
    
    /*@Column(name="group_id", nullable=false)
    private Integer groupId; */
    
    //@NotEmpty
    @Column(name="mail")
    private String mail;
    
    @Column(name="is_captain", columnDefinition="BIT")
    private Boolean isCaptain = false;
    
    @Column(name="by_mail", columnDefinition="BIT")
    private Boolean byMail;
    
    @Column(name="by_vk", columnDefinition="BIT")
    private Boolean byVK;

    public Boolean getByMail() {
        return byMail;
    }

    public void setByMail(Boolean byMail) {
        this.byMail = byMail;
    }

    public Boolean getByVK() {
        return byVK;
    }

    public void setByVK(Boolean byVK) {
        this.byVK = byVK;
    }
      
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group group;//группа, которой принадлжеит студент

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
       
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUid() {
        return uid;
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

    /*public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }*/
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Boolean isCaptain) {
        this.isCaptain = isCaptain;
    }    
}
