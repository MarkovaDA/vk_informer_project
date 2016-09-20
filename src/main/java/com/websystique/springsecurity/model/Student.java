
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
    @Column(name="vk_id", unique=true, nullable=false)
    private String vk_id;
    
    @NotEmpty
    @Column(name="first_name", nullable=false)
    private String firstName;
    
    @NotEmpty
    @Column(name="last_name", nullable=false)
    private String lastName;
    
    /*@Column(name="group_id", nullable=false)
    private Integer groupId; */
    
    @NotEmpty
    @Column(name="mail", nullable=false)
    private String mail;
    
    @Column(name="is_captain", columnDefinition="BIT")
    private Boolean isCaptain;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group group;//группа, которой принадлжеит студент

    
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
    
    public String getVk_id() {
        return vk_id;
    }

    public void setVk_id(String vk_id) {
        this.vk_id = vk_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
