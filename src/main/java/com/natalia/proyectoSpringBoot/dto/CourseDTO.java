package com.natalia.proyectoSpringBoot.dto;

import com.natalia.proyectoSpringBoot.models.Career;
import lombok.Data;

@Data
public class CourseDTO {
    private String name;
    private Career career;
}
