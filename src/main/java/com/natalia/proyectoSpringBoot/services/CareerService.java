package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.models.Career;

import java.util.List;

public interface CareerService {
    List<String> getNames();
    List<Career> getNamesWithPagination(int offset, int pageSize);
    List<Career> getCareers();
    Career add(Career career);
    void deleteByName(String nameCareer);
    void deleteById(Long idCareer);
    void updateByName(String nameCareer, String newNameCareer);
    Career updateById(Career career, Long idCareer);

}
