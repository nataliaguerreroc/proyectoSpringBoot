package com.natalia.proyectoSpringBoot.dto;
import com.natalia.proyectoSpringBoot.models.Course;

import lombok.Data;

@Data
public class TopicDTO {
    String nameTopic;

    String description;

    private Course course;
}
