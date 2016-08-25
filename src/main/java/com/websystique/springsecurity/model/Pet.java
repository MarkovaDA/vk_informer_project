package com.websystique.springsecurity.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pets")
public class Pet implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    @Column(name="owner_id")
    private Integer ownerId; //владелец животного
    
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }    
    /*@ManyToOne
    @JoinColumn(name="owner_id", referencedColumnName="id")
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }*/

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
    
    @Column(name="name")
    private String name;
    
    @Column(name="descr")
    private String descr;
    
         
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pets_issues", 
            joinColumns = @JoinColumn(name = "pet_id",referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(name = "issue_id",referencedColumnName = "id")
    )
    private List<Issue> issues; //все обращения по животному
    
    /*@OneToMany(fetch = FetchType.EAGER, mappedBy="pet")
    private List<Image> images; */

    /*public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }*/
       
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    
}
