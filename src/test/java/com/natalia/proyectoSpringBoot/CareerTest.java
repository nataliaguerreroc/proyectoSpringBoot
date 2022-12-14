package com.natalia.proyectoSpringBoot;

import com.natalia.proyectoSpringBoot.dto.UserDTO;
import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.repositories.CareerRepository;
import com.natalia.proyectoSpringBoot.repositories.UserRepository;
import com.natalia.proyectoSpringBoot.services.CareerServiceImpl;
import com.natalia.proyectoSpringBoot.services.CourseServiceImpl;
import com.natalia.proyectoSpringBoot.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CareerTest {

    @Test
    public void test1(){

        Career career = new Career();
        career.setNameCareer("Biologia");
        career.setIdCareer(2L);

        Assertions.assertEquals(career.getNameCareer(), "Biologia");
        Assertions.assertEquals(career.getIdCareer(), 2L);

        User u1;
        u1 = new User();
        //UserService userService = new UserService();

        u1.setName("Ana");
        u1.setEmail("ana@gmail.com");
        u1.setPassword("123");
        u1.setCareer(career);

        Assertions.assertEquals(u1.getName(), "Ana");
        Assertions.assertEquals(u1.getCareer().getNameCareer(), "Biologia");

        Course c1;
        c1 = new Course();

        c1.setName("Calculo");
        c1.setIdCourse(3L);
        //c1.setCareer(career);

        Assertions.assertEquals(c1.getIdCourse(), 3L);
        //Assertions.assertEquals(c1.getCareer(), "");




    }

}
