package com.natalia.proyectoSpringBoot.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(columnDefinition="VARCHAR(15)")
    private Long idCourse;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="career_id", nullable=false)
    private Career career;

}
