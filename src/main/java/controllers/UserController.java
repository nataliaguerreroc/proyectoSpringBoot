package controllers;

import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/users/info")
    public List <Map<String, String>> getUsersInfo(){
        return this.userService.getUsersInfo();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody Map<String, String> json){
        return this.userService.add(json.get("name"), json.get("email"), json.get("password"), json.get("career"));
    }

    /*
    @PatchMapping(value = "/users/{idUser}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody Map<String, String> json,@PathVariable Long idUser){
        return this.userService.updateById(json.get("newName"),json.get("newEmail"),json.get("newPassword"),json.get("nameCareer"),idUser);

    }*/

    @PutMapping(value = "/users/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user, @PathVariable Long idUser){
        return this.userService.updateById(user, idUser);
    }

    @DeleteMapping(value = "/users/{idUser}")
    public void deleteUser(@PathVariable Long idUser){
        this.userService.deleteById(idUser);
    }



}
