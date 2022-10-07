package controllers;

import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.services.CourseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getCourses(){
        return this.courseService.getCourses();
    }

    @GetMapping("/courses/info")
    public List <Map<String, String>> getCoursesInfo(){
        return this.courseService.getCoursesInfo();
    }

    @PostMapping(value = "/courses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course createCourse(@RequestBody Map<String, String> json){
        return this.courseService.add(json.get("name"), json.get("career"));
    }

    @PutMapping(value = "/courses/{idCourse}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course updateCourse(@RequestBody Course course, @PathVariable Long idCourse){
        return this.courseService.updateById(course, idCourse);
    }

    @DeleteMapping(value = "/courses/{idCourse}")
    public void deleteCourse(@PathVariable Long idCourse){
        this.courseService.deleteById(idCourse);
    }

}
