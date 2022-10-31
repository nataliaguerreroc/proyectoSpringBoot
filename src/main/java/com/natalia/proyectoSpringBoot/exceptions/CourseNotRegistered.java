package com.natalia.proyectoSpringBoot.exceptions;

public class CourseNotRegistered extends RuntimeException {
    private Long idCourse;

    public CourseNotRegistered(Long idCourse){
        this.idCourse = idCourse;
    }

    public Long getIdCourse(){
        return idCourse;
    }


}
