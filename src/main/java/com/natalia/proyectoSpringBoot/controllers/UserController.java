package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.dto.UserDTO;
import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public List<User> getUsersWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        return this.userService.getUsersWithPagination(offset, pageSize);
    }

    @GetMapping("/{info}")
    public List <UserDTO> getUsersInfo(){
        return this.userService.getUsersInfo();
    }

    @PostMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@Valid @RequestBody Map<String, String> json){
        return this.userService.add(json.get("name"), json.get("email"), json.get("password"), json.get("career"));
    }

    @PutMapping(value = "/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user, @PathVariable Long idUser){
        return this.userService.updateById(user, idUser);
    }

    @DeleteMapping(value = "/{idUser}")
    public void deleteUser(@PathVariable Long idUser){
        this.userService.deleteById(idUser);
    }



}
