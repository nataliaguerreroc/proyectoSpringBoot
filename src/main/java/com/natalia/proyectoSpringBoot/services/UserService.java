package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.User;
import com.natalia.proyectoSpringBoot.repositories.ICareerRepository;
import com.natalia.proyectoSpringBoot.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final IUserRepository iuserRepository;
    private final ICareerRepository icareerRepository;

    public UserService(IUserRepository iuserRepository, ICareerRepository icareerRepository){
        this.iuserRepository = iuserRepository;
        this.icareerRepository = icareerRepository;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        this.iuserRepository.findAll().forEach(users::add);
        return users;
    }

    public List <Map<String, String>> getUsersInfo(){
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
    }

    public User add(String name, String email, String password, String nameCareer){
        User users = new User(name, email, password);
        List<Career> career = this.icareerRepository.findByName(nameCareer);
        if (career.size() > 0){
            Career c = career.get(0);
            users.setCareer(c);
        }
        users = this.iuserRepository.save(users);
        System.out.println("User added JSON body");
        return users;

    }

    public void deleteByName(String name){
        List<User> users = this.iuserRepository.findByName(name);
        if (users.size() > 0){
            User u = users.get(0);
            this.iuserRepository.deleteById(u.getIdUser());
            System.out.println("User deleted JSON body");

        }
    }

    public void deleteById(Long idUser){
        this.iuserRepository.deleteById(idUser);
    }

    public void updateByName(String name, String newName, String newEmail,String newPassword, String nameCareer){
        List<User> users = this.iuserRepository.findByName(name);
        if(users.size() > 0){
            User u = users.get(0);
            u.setName(newName);
            u.setEmail(newEmail);
            u.setPassword(newPassword);
            List<Career> careers = this.icareerRepository.findByName(nameCareer);
            if(careers.size() > 0){
                Career c = careers.get(0);
                u.setCareer(c);
            }
            this.iuserRepository.save(u);
            System.out.println("User updated JSON body");
        }
    }

    public User updateById(User user, Long idUser){
        Optional<User> optionalUser = this.iuserRepository.findById(idUser);
        User oldUser = null;
        if(optionalUser.isPresent()){
            oldUser = optionalUser.get();
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            List<Career> career = this.icareerRepository.findByName(user.getCareer().getNameCareer());
            if(career.size() > 0){
                Career d = career.get(0);
                oldUser.setCareer(d);
            }
            oldUser = this.iuserRepository.save(oldUser);
        }
        return oldUser;
    }

    /*public User updateById(String newName, String newEmail, String newPassword, String nameCareer, Long idUser){
        Optional<User> userOptional = this.iuserRepository.findById(idUser);
        User user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
            user.setName(newName);
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            List<Career> careers = this.icareerRepository.findByName(nameCareer);
            if(careers.size() > 0){
                Career c = careers.get(0);
                user.setCareer(c);
            }
            user = this.iuserRepository.save(user);

        }
        return user;
    }*/

}
