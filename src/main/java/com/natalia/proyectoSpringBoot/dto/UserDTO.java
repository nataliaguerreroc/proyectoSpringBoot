package com.natalia.proyectoSpringBoot.dto;

import com.natalia.proyectoSpringBoot.models.Career;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private Career career;
}
