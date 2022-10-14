package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.dto.UserDTO;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final IUserService iuserService;

    public UserController(IUserService iuserService){
        this.iuserService = iuserService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return this.iuserService.getUsers();
    }

    @GetMapping("/users/info")
    public List <UserDTO> getUsersInfo(){
        return this.iuserService.getUsersInfo();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody Map<String, String> json){
        return this.iuserService.add(json.get("name"), json.get("email"), json.get("password"), json.get("career"));
    }

    @PutMapping(value = "/users/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user, @PathVariable Long idUser){
        return this.iuserService.updateById(user, idUser);
    }

    @DeleteMapping(value = "/users/{idUser}")
    public void deleteUser(@PathVariable Long idUser){
        this.iuserService.deleteById(idUser);
    }



}
