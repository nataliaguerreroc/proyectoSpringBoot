package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.dto.CourseDTO;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.services.CourseServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
    private final CourseServiceImpl courseServiceImpl;

    public CourseController(CourseServiceImpl courseServiceImpl){
        this.courseServiceImpl = courseServiceImpl;
    }

    @GetMapping("/courses")
    public List<Course> getCourses(){
        return this.courseServiceImpl.getCourses();
    }

    @GetMapping("/courses/info")
    public List <CourseDTO> getCoursesInfo(){
        return this.courseServiceImpl.getCoursesInfo();
    }

    @PostMapping(value = "/courses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course createCourse(@RequestBody Map<String, String> json){
        return this.courseServiceImpl.add(json.get("name"), json.get("career"));
    }

    @PutMapping(value = "/courses/{idCourse}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course updateCourse(@RequestBody Course course, @PathVariable Long idCourse){
            return this.courseServiceImpl.updateById(course, idCourse);
    }

    @DeleteMapping(value = "/courses/{idCourse}")
    public void deleteCourse(@PathVariable Long idCourse){
            this.courseServiceImpl.deleteById(idCourse);

    }

}
