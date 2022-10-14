package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.dto.CourseDTO;
import com.natalia.proyectoSpringBoot.models.Course;

import java.util.List;
import java.util.Map;

public interface ICourseService {
    List<Course> getCourses();

    List <CourseDTO> getCoursesInfo();

    Course add(String name, String nameCareer);

    void deleteByName(String name);

    void deleteById(Long idCourse);

    void updateByName(String name, String newName, String nameCareer);

    Course updateById(Course course, Long idCourse);



}
