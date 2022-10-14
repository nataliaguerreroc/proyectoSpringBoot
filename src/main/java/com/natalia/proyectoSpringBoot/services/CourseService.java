package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.dto.CourseDTO;
import com.natalia.proyectoSpringBoot.exceptions.CourseNotRegistered;
import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.repositories.ICareerRepository;
import com.natalia.proyectoSpringBoot.repositories.ICourseRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {
    private final ICourseRepository icourseRepository;
    private final ICareerRepository icareerRepository;

    public CourseService(ICourseRepository icourseRepository, ICareerRepository icareerRepository){
        this.icourseRepository = icourseRepository;
        this.icareerRepository = icareerRepository;
    }

    public List<Course> getCourses(){
        List<Course> courses = new ArrayList<>();
        this.icourseRepository.findAll().forEach(courses::add);
        return courses;
    }

    public List<CourseDTO> getCoursesInfo() {
        List <Course> data = icourseRepository.getCoursesInfo();

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
        List<Career> career = this.icareerRepository.findByName(nameCareer);
        if (career.size() > 0){
            Career c = career.get(0);
            courses.setCareer(c);
        }
        courses = this.icourseRepository.save(courses);
        System.out.println("User added JSON body");
        return courses;

    }

    public void deleteByName(String name){
        List<Course> courses = this.icourseRepository.findByName(name);
        if (courses.size() > 0){
            Course o = courses.get(0);
            this.icourseRepository.deleteById(o.getIdCourse());
            System.out.println("User deleted JSON body");

        }
    }

    public void deleteById(Long idCourse){
        this.icourseRepository.deleteById(idCourse);
    }

    public void updateByName(String name, String newName, String nameCareer) {
            List<Course> courses = this.icourseRepository.findByName(name);
            if (courses.size() > 0) {
                Course o = courses.get(0);
                o.setName(newName);
                List<Career> careers = this.icareerRepository.findByName(nameCareer);
                if (careers.size() > 0) {
                    Career c = careers.get(0);
                    o.setCareer(c);
                }
                this.icourseRepository.save(o);
                System.out.println("User updated JSON body");
            }
    }

    public Course updateById(Course course, Long idCourse) {
            Optional<Course> optionalCourse = this.icourseRepository.findById(idCourse);
            Course oldCourse = null;
            //if (optionalCourse.isPresent()) {
                oldCourse = optionalCourse.orElseThrow( () -> new CourseNotRegistered(idCourse));
                oldCourse.setName(course.getName());
                List<Career> career = this.icareerRepository.findByName(course.getCareer().getNameCareer());
                if (career.size() > 0) {
                    Career d = career.get(0);
                    oldCourse.setCareer(d);
                }
                oldCourse = this.icourseRepository.save(oldCourse);
            //}
            return oldCourse;
    }

}
