package com.natalia.proyectoSpringBoot.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class User {
    @Id
    @Column(columnDefinition="VARCHAR(15)")
    private Long idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name="career_id", nullable=false)
    private Career career;


}
