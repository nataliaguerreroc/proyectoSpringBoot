package com.natalia.proyectoSpringBoot.bootstrap;
import org.springframework.boot.CommandLineRunner;
import com.natalia.proyectoSpringBoot.models.*;
import com.natalia.proyectoSpringBoot.repositories.*;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CareerRepository careerRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;


    public BootStrapData(CareerRepository careerRepository, CourseRepository courseRepository, UserRepository userRepository){
        this.careerRepository = careerRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Career career1 = new Career("Biologia");
        Career career2 = new Career("Ingenieria de Sistemas");

        careerRepository.save(career1);
        careerRepository.save(career2);

        Course course1 = new Course("Calculo");
        Course course2 = new Course("Modelado de Sistemas");
        course1.setCareer(career1);
        course2.setCareer(career2);

        courseRepository.save(course1);
        courseRepository.save(course2);

        System.out.println("Total courses: " + courseRepository.count());

        User user1 = new User("user1", "user1@gmail.com", "pass1");
        User user2 = new User("user2", "user2@gmail.com", "pass2");
        user1.setCareer(career1);
        user2.setCareer(career2);

        userRepository.save(user1);
        userRepository.save(user2);



            /*career1.getCourses().add(course1);
            career2.getCourses().add(course2);

            career1.getUsers().add(user1);
            career2.getUsers().add(user2);*/


    }
}

