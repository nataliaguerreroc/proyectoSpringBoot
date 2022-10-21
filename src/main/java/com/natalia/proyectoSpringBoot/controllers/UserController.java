package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.dto.UserDTO;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.services.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return this.userServiceImpl.getUsers();
    }

    @GetMapping("/users/info")
    public List <UserDTO> getUsersInfo(){
        return this.userServiceImpl.getUsersInfo();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody Map<String, String> json){
        return this.userServiceImpl.add(json.get("name"), json.get("email"), json.get("password"), json.get("career"));
    }

    @PutMapping(value = "/users/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user, @PathVariable Long idUser){
        return this.userServiceImpl.updateById(user, idUser);
    }

    @DeleteMapping(value = "/users/{idUser}")
    public void deleteUser(@PathVariable Long idUser){
        this.userServiceImpl.deleteById(idUser);
    }



}
