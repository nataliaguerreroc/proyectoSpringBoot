package com.natalia.proyectoSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCareer;
    @Column(name = "nameCareer", unique=true, nullable = false)
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
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonManagedReference(value = "course-career")
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


}

