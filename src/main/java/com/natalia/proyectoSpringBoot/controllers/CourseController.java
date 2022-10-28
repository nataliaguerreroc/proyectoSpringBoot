package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.dto.CourseDTO;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.services.CourseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping()
    public List<Course> getCourses(){
        return this.courseService.getCourses();
    }

    @GetMapping("/{info}")
    public List <CourseDTO> getCoursesInfo(){
        return this.courseService.getCoursesInfo();
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public List<Course> getCourseWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        return this.courseService.getCourseWithPagination(offset, pageSize);
    }

    @PostMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course createCourse(@RequestBody Map<String, String> json){
        return this.courseService.add(json.get("name"), json.get("career"));
    }

    @PutMapping(value = "/{idCourse}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course updateCourse(@RequestBody Course course, @PathVariable Long idCourse){
            return this.courseService.updateById(course, idCourse);
    }

    @DeleteMapping(value = "/{idCourse}")
    public void deleteCourse(@PathVariable Long idCourse){
            this.courseService.deleteById(idCourse);

    }

}
