package com.natalia.proyectoSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Data
@Entity
@Table(name = "career")
@ToString(exclude = {"nameCareer"})
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCareer;
    @Column(name = "nameCareer", unique=true)
    private String nameCareer;

    @OneToMany(mappedBy="career")
    //@JsonManagedReference
    private List<User> users;

    @OneToMany(mappedBy="career")
    private List<Course> courses;

    public Career(String nameCareer){
        this.nameCareer = nameCareer;
    }
    public Career() {
    }

    @JsonManagedReference(value = "user-career")
    //@JsonManagedReference
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonManagedReference(value = "course-career")
    //@JsonManagedReference
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /*@JsonManagedReference(value = "user-career")
    public Set<User> getUsers() {
        return users;
    }*/

    /*@JsonManagedReference(value = "course-career")
    public Set<Course> getCourse() {
        return courses;
    }*/


}

