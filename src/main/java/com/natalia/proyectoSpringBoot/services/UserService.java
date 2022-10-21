package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.dto.UserDTO;
import com.natalia.proyectoSpringBoot.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    List <UserDTO> getUsersInfo();

    User add(String name, String email, String password, String nameCareer);

    void deleteByName(String name);

    void deleteById(Long idUser);

    void updateByName(String name, String newName, String newEmail,String newPassword, String nameCareer);

    User updateById(User user, Long idUser);
}
