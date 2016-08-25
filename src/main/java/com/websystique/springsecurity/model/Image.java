package com.websystique.springsecurity.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="pets_images")
public class Image implements Serializable{
    
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "pk_image_gen")
    @SequenceGenerator(name = "pk_image_gen", sequenceName = "pet_img_seq", allocationSize=1)
    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @NotNull
    @Column(name="pet_id")
    private Integer petId; 
    
    @NotEmpty
    @Column(name="image_id")
    private String imageId;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet; //питомец,которому принадлжеит изображение

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }*/
      
    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    } 
}
