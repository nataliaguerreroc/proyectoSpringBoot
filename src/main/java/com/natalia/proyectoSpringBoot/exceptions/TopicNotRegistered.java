package com.natalia.proyectoSpringBoot.exceptions;

public class TopicNotRegistered extends RuntimeException{

    private Long idTopic;

    public TopicNotRegistered(Long idTopic){
        this.idTopic = idTopic;
    }

    public Long getIdTopic(){
        return idTopic;
    }
}
