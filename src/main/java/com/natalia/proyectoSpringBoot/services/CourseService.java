package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.dto.CourseDTO;
import com.natalia.proyectoSpringBoot.exceptions.CourseNotRegistered;
import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.repositories.CareerRepositoryImpl;
import com.natalia.proyectoSpringBoot.repositories.CourseRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService implements CourseServiceImpl {
    private final CourseRepositoryImpl courseRepositoryImpl;
    private final CareerRepositoryImpl careerRepositoryImpl;

    public CourseService(CourseRepositoryImpl courseRepositoryImpl, CareerRepositoryImpl careerRepositoryImpl){
        this.courseRepositoryImpl = courseRepositoryImpl;
        this.careerRepositoryImpl = careerRepositoryImpl;
    }

    // getCourses() only shows all the courses registered and their information, like ID course
    public List<Course> getCourses(){
        List<Course> courses = new ArrayList<>();
        this.courseRepositoryImpl.findAll().forEach(courses::add);
        return courses;
    }

    // getCoursesInfo() not only shows the course information, but also shows the career information and the user
    // enrolled in it
    public List<CourseDTO> getCoursesInfo() {
        List <Course> data = courseRepositoryImpl.getCoursesInfo();

        List<CourseDTO> courseTempList = data.stream()
                .map(o ->{
                    CourseDTO dto = new CourseDTO();
                    dto.setName(o.getName());
                    dto.setCareer(o.getCareer());
                    return dto;
                })
                .collect(Collectors.toList());
        return courseTempList;
    }

    public Course add(String name, String nameCareer){
        Course courses = new Course(name);
        List<Career> career = this.careerRepositoryImpl.findByName(nameCareer);
        if (career.size() > 0){
            Career c = career.get(0);
            courses.setCareer(c);
        }
        courses = this.courseRepositoryImpl.save(courses);
        System.out.println("User added JSON body");
        return courses;

    }

    public void deleteByName(String name){
        List<Course> courses = this.courseRepositoryImpl.findByName(name);
        if (courses.size() > 0){
            Course o = courses.get(0);
            this.courseRepositoryImpl.deleteById(o.getIdCourse());
            System.out.println("User deleted JSON body");

        }
    }


    public void deleteById(Long idCourse){
        Optional<Course> optionalCourse = this.courseRepositoryImpl.findById(idCourse);
        Course oldCourse = null;
        oldCourse = optionalCourse.orElseThrow( () -> new CourseNotRegistered(idCourse));

        this.courseRepositoryImpl.deleteById(idCourse);
    }


    public void updateByName(String name, String newName, String nameCareer) {
            List<Course> courses = this.courseRepositoryImpl.findByName(name);
            if (courses.size() > 0) {
                Course o = courses.get(0);
                o.setName(newName);
                List<Career> careers = this.careerRepositoryImpl.findByName(nameCareer);
                if (careers.size() > 0) {
                    Career c = careers.get(0);
                    o.setCareer(c);
                }
                this.courseRepositoryImpl.save(o);
                System.out.println("User updated JSON body");
            }
    }

    public Course updateById(Course course, Long idCourse) {
            Optional<Course> optionalCourse = this.courseRepositoryImpl.findById(idCourse);
            Course oldCourse = null;
            //if (optionalCourse.isPresent()) {
                oldCourse = optionalCourse.orElseThrow( () -> new CourseNotRegistered(idCourse));
                //oldCourse = optionalCourse.get();
                oldCourse.setName(course.getName());
                List<Career> career = this.careerRepositoryImpl.findByName(course.getCareer().getNameCareer());
                if (career.size() > 0) {
                    Career d = career.get(0);
                    oldCourse.setCareer(d);
                }
                oldCourse = this.courseRepositoryImpl.save(oldCourse);
            //}
            return oldCourse;
    }

}
