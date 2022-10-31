package com.natalia.proyectoSpringBoot;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.natalia.proyectoSpringBoot.controllers.UserController;
import com.natalia.proyectoSpringBoot.models.*;
import com.natalia.proyectoSpringBoot.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    MockMvc mockMvc;

    User user1, user2;

    @Mock
    UserServiceImpl userServiceimpl;

    @InjectMocks // inject mock userServiceImpl
    UserController userController;

    @BeforeEach
    void setUp(){
        //Create users
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();

        Career career1 = new Career("career 1");
        Career career2 = new Career("career 2");

        User user1 = new User("user1", "user1@gmail.com", "pass1");
        User user2 = new User("user2", "user2@gmail.com", "pass2");
        user1.setCareer(career1);
        user2.setCareer(career2);

        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();//Make sure that is using mockito and not tomcat web server


    }

    @Test
    void getUsers() throws Exception{
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        Mockito.when(userServiceimpl.getUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

}
