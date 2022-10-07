package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.repositories.ICareerRepository;
import com.natalia.proyectoSpringBoot.repositories.ICourseRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {
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

    public List <Map<String, String>> getCoursesInfo(){
        List <Map<String, String>> jsons = new ArrayList<Map<String, String>>();

        this.icourseRepository.getCoursesInfo().stream().forEach(o ->{
            Map<String, String> map = new HashMap<>();
            map.put("Name", o.getName());
            map.put("Career",o.getCareer() == null ? "null career": (o.getCareer().getNameCareer().length() > 0 ?o.getCareer().getNameCareer(): "null career"));
            jsons.add(map);
        });

        return jsons;
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

    public void updateByName(String name, String newName, String nameCareer){
        List<Course> courses = this.icourseRepository.findByName(name);
        if(courses.size() > 0){
            Course o = courses.get(0);
            o.setName(newName);
            List<Career> careers = this.icareerRepository.findByName(nameCareer);
            if(careers.size() > 0){
                Career c = careers.get(0);
                o.setCareer(c);
            }
            this.icourseRepository.save(o);
            System.out.println("User updated JSON body");
        }
    }

    public Course updateById(Course course, Long idCourse){
        Optional<Course> optionalCourse = this.icourseRepository.findById(idCourse);
        Course oldCourse = null;
        if(optionalCourse.isPresent()){
            oldCourse = optionalCourse.get();
            oldCourse.setName(course.getName());
            List<Career> career = this.icareerRepository.findByName(course.getCareer().getNameCareer());
            if(career.size() > 0){
                Career d = career.get(0);
                oldCourse.setCareer(d);
            }
            oldCourse = this.icourseRepository.save(oldCourse);
        }
        return oldCourse;
    }


}
