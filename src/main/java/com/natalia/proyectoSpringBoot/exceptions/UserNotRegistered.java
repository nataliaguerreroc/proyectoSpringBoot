package com.natalia.proyectoSpringBoot.exceptions;

public class UserNotRegistered extends RuntimeException {

    private Long idUser;

    public UserNotRegistered(Long idUser){
        this.idUser = idUser;
    }

    public Long getIdUser(){
        return idUser;
    }

}
