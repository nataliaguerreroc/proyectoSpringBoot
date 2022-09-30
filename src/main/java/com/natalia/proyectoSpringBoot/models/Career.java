package com.natalia.proyectoSpringBoot.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "career")
public class Career {
    @Id
    @Column(columnDefinition="VARCHAR(15)")
    private Long idCareer;

    @Column(name = "nameCareer", unique=true)
    private String nameCareer;

    /*@OneToOne
    private User user;
    */

    @OneToMany(mappedBy="career")
    private Set<User> users;

    @OneToMany(mappedBy="career")
    private Set<Course> courses;

    /*@OneToMany
    @JoinColumn(name = "career_id")
    private Set<Course> course = new HashSet<>();
 */
    //Constructor
    public Career (String nameCareer){
        this.nameCareer = nameCareer;
    }


}

