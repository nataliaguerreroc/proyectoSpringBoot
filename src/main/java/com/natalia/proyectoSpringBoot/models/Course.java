package com.natalia.proyectoSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCourse;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="id_career", nullable=false)
    private Career career;

    @OneToMany(mappedBy="course")
    private List<Topic> topic;


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

    @JsonManagedReference(value = "topic-course")
    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }


}
