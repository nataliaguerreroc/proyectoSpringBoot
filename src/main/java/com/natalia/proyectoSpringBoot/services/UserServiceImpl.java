package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.dto.UserDTO;
import com.natalia.proyectoSpringBoot.exceptions.UserNotRegistered;
import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.repositories.CareerRepository;
import com.natalia.proyectoSpringBoot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CareerRepository careerRepository;

    public UserServiceImpl(UserRepository userRepository, CareerRepository careerRepository){
        this.userRepository = userRepository;
        this.careerRepository = careerRepository;
    }

    //getUsers() only shows all the users (students) registered and their information
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    /*public List <Map<String, String>> getUsersInfo(){
        List <Map<String, String>> jsons = new ArrayList<Map<String, String>>();

        this.iuserRepository.getUsersInfo().stream().forEach(u ->{
            Map<String, String> map = new HashMap<>();
            map.put("Name", u.getName());
            map.put("Email", u.getEmail());
            map.put("Password", u.getPassword());
            map.put("Career",u.getCareer() == null ? "null career": (u.getCareer().getNameCareer().length() > 0 ?u.getCareer().getNameCareer(): "null career"));
            jsons.add(map);
        });

        return jsons;
    }*/

    //getUsersInfo() not only shows the user information, but also shows the career information
    // that the user enrolled, this includes information about the courses of the career and the other users enrolled
    public List<UserDTO> getUsersInfo(){
        List<User> data = userRepository.getUsersInfo();

        List<UserDTO> userTempList = data.stream()
                .map(u ->{
                    UserDTO dto = new UserDTO();
                    dto.setName(u.getName());
                    dto.setEmail(u.getEmail());
                    dto.setPassword(u.getPassword());
                    dto.setCareer(u.getCareer());
                    return dto;
                })
                .collect(Collectors.toList());
        return userTempList;
    }


    public User add(String name, String email, String password, String nameCareer){
        User users = new User(name, email, password);
        List<Career> career = this.careerRepository.findByName(nameCareer);
        if (career.size() > 0){
            Career c = career.get(0);
            users.setCareer(c);
        }
        users = this.userRepository.save(users);
        System.out.println("User added JSON body");
        return users;

    }

    public void deleteByName(String name){
        List<User> users = this.userRepository.findByName(name);
        if (users.size() > 0){
            User u = users.get(0);
            this.userRepository.deleteById(u.getIdUser());
            System.out.println("User deleted JSON body");

        }
    }

    public void deleteById(Long idUser){
        Optional<User> optionalUser = this.userRepository.findById(idUser);
        User oldUser = null;
        oldUser = optionalUser.orElseThrow( () -> new UserNotRegistered(idUser));

        this.userRepository.deleteById(idUser);
    }

    public void updateByName(String name, String newName, String newEmail,String newPassword, String nameCareer){
        List<User> users = this.userRepository.findByName(name);
        if(users.size() > 0){
            User u = users.get(0);
            u.setName(newName);
            u.setEmail(newEmail);
            u.setPassword(newPassword);
            List<Career> careers = this.careerRepository.findByName(nameCareer);
            if(careers.size() > 0){
                Career c = careers.get(0);
                u.setCareer(c);
            }
            this.userRepository.save(u);
            System.out.println("User updated JSON body");
        }
    }

    public User updateById(User user, Long idUser){
        Optional<User> optionalUser = this.userRepository.findById(idUser);
        User oldUser = null;
        //if(optionalUser.isPresent()){
            oldUser = optionalUser.orElseThrow( () -> new UserNotRegistered(idUser));
            //oldUser = optionalUser.get();
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            List<Career> career = this.careerRepository.findByName(user.getCareer().getNameCareer());
            if(career.size() > 0){
                Career d = career.get(0);
                oldUser.setCareer(d);
            }
            oldUser = this.userRepository.save(oldUser);
        //}
        return oldUser;
    }

}
