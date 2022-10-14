package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.models.Career;

import java.util.List;

public interface ICareerService {
    List<String> getNames();
    List<Career> getCareers();
    Career add(Career career);
    void deleteByName(String nameCareer);
    void deleteById(Long idCareer);
    void updateByName(String nameCareer, String newNameCareer);
    Career updateById(Career career, Long idCareer);

}
