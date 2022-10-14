package com.natalia.proyectoSpringBoot.dto;

import com.natalia.proyectoSpringBoot.models.Career;
import lombok.Data;


@Data
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Career career;
}
