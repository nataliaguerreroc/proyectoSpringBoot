package com.natalia.proyectoSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
//@ToString(exclude = {"career"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name="id_career", nullable=false)
    //@JsonBackReference
    private Career career;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User() {
    }

    @JsonBackReference(value = "user-career")
    //@JsonBackReference
    public Career getCareer(){
        return career;
    }

    public void setCareer(Career career){
        this.career = career;
    }

}
