package com.natalia.proyectoSpringBoot.dto;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.Topic;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private String name;
    private Career career;
    private List<Topic> topics;
}
