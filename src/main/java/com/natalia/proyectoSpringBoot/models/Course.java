package com.natalia.proyectoSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCourse;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="id_career", nullable=false)
    private Career career;

    public Course(String name){
        this.name = name;
    }

    public Course() {
    }

    @JsonBackReference(value = "course-career")
    //@JsonBackReference
    public Career getCareer(){
        return career;
    }

    public void setCareer(Career career){
        this.career = career;
    }


}
