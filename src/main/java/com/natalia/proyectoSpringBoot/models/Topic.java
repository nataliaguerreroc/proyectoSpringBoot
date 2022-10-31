package com.natalia.proyectoSpringBoot.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTopic;

    @Column(name = "nameTopic", unique=true, nullable = false)
    private String nameTopic;

    @Column(name = "description", unique=true, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="id_course", nullable=false)
    private Course course;

    public Topic(String nameTopic, String description){
        this.nameTopic = nameTopic;
        this.description = description;
    }

    public Topic(){
    }

    @JsonBackReference(value = "topic-course")
    //@JsonBackReference
    public Course getCourse(){
        return course;
    }

    public void setCourse(Course course){
        this.course = course;
    }



}
