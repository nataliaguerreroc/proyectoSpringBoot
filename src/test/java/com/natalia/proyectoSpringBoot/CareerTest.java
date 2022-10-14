package com.natalia.proyectoSpringBoot;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.repositories.ICareerRepository;
import com.natalia.proyectoSpringBoot.services.CareerService;
import com.natalia.proyectoSpringBoot.services.ICareerService;
import com.natalia.proyectoSpringBoot.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CareerTest {

    @Test
    public void test1(){
        ICareerRepository icareerRepository = null;
        CareerService careerService = new CareerService(null);

        Career career = new Career();
        career.setNameCareer("Biologia");
        career.setIdCareer(2L);
        icareerRepository.save(career);


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
