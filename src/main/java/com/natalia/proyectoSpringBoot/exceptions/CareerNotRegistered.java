package com.natalia.proyectoSpringBoot.exceptions;

public class CareerNotRegistered extends RuntimeException{
    private Long idCareer;

    public CareerNotRegistered(Long idCareer){
        this.idCareer = idCareer;
    }

    public Long getIdCareer(){
        return idCareer;
    }

}
