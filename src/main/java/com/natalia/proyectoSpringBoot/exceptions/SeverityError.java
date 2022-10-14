package com.natalia.proyectoSpringBoot.exceptions;

public enum SeverityError {
    URGENT("URGENT"),
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private String text;

    SeverityError(String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
