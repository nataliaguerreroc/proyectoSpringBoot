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

        Career career1 = new Career("career 1");
        Career career2 = new Career("career 2");
        Career career3 = new Career("career 3");
        Career career4 = new Career("career 4");
        Career career5 = new Career("career 5");
        Career career6 = new Career("career 6");
        Career career7 = new Career("career 7");
        Career career8 = new Career("career 8");
        Career career9 = new Career("career 9");
        Career career10 = new Career("career 10");

        careerRepository.save(career1);
        careerRepository.save(career2);
        careerRepository.save(career3);
        careerRepository.save(career4);
        careerRepository.save(career5);
        careerRepository.save(career6);
        careerRepository.save(career7);
        careerRepository.save(career8);
        careerRepository.save(career9);
        careerRepository.save(career10);


        Course course1 = new Course("course 1");
        Course course2 = new Course("course 2");
        Course course3 = new Course("course 3");
        Course course4 = new Course("course 4");
        Course course5 = new Course("course 5");
        Course course6 = new Course("course 6");
        Course course7 = new Course("course 7");
        Course course8 = new Course("course 8");
        course1.setCareer(career1);
        course2.setCareer(career2);
        course3.setCareer(career3);
        course4.setCareer(career4);
        course5.setCareer(career5);
        course6.setCareer(career6);
        course7.setCareer(career7);
        course8.setCareer(career7);

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);
        courseRepository.save(course5);
        courseRepository.save(course6);
        courseRepository.save(course7);
        courseRepository.save(course8);


        System.out.println("Total courses: " + courseRepository.count());

        User user1 = new User("user1", "user1@gmail.com", "pass1");
        User user2 = new User("user2", "user2@gmail.com", "pass2");
        User user3 = new User("user3", "user3@gmail.com", "pass3");
        User user4 = new User("user4", "user4@gmail.com", "pass4");
        User user5 = new User("user5", "user5@gmail.com", "pass5");
        User user6 = new User("user6", "user6@gmail.com", "pass6");
        User user7 = new User("user7", "user7@gmail.com", "pass7");
        User user8 = new User("user8", "user8@gmail.com", "pass8");
        User user9 = new User("user9", "user9@gmail.com", "pass9");
        user1.setCareer(career1);
        user2.setCareer(career2);
        user3.setCareer(career3);
        user4.setCareer(career4);
        user5.setCareer(career5);
        user6.setCareer(career6);
        user7.setCareer(career7);
        user8.setCareer(career8);
        user9.setCareer(career8);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);



            /*career1.getCourses().add(course1);
            career2.getCourses().add(course2);

            career1.getUsers().add(user1);
            career2.getUsers().add(user2);*/


    }
}

