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
import javax.persistence.Table;

@Entity
@Table(name="pets")
public class Pet implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    /*@Column(name="owner_id")
    private Integer ownerId; //владелец животного*/
    
    @ManyToOne
    @JoinColumn(name="owner_id", referencedColumnName="id")
    private User owner;
    
    @Column(name="name")
    private String name;
    
    @Column(name="descr")
    private String descr;
    
         
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pets_issues", 
            joinColumns = @JoinColumn(name = "pet_id",referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(name = "issue_id",referencedColumnName = "id")
            )
    private List<Issue> issues; //все обращения по животному

    public List<Issue> getUssues() {
        return issues;
    }

    public void setUssues(List<Issue> issues) {
        this.issues = issues;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }*/

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
