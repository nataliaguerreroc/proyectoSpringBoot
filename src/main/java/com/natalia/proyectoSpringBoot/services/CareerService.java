package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.repositories.ICareerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CareerService implements ICareerService{
    private final ICareerRepository icareerRepository;

    public CareerService(ICareerRepository icareerRepository) {
        this.icareerRepository = icareerRepository;
    }

    public List<String> getNames(){
        return this.icareerRepository.getNames();
    }

    public List<Career> getCareers(){
        List<Career> careers = new ArrayList<>();
        this.icareerRepository.findAll().forEach(careers::add);
        return careers;
    }

    public Career add(Career career){
        return this.icareerRepository.save(career);
    }

    public void deleteByName(String nameCareer){
        List<Career> careers = this.icareerRepository.findByName(nameCareer);
        if (careers.size() > 0){
            Career c = careers.get(0);
            this.icareerRepository.deleteById(c.getIdCareer());
            System.out.println("Career deleted JSON body");
        }
    }

    public void deleteById(Long idCareer){
        this.icareerRepository.deleteById(idCareer);
    }

    public void updateByName(String nameCareer, String newNameCareer){
        List<Career> careers = this.icareerRepository.findByName(nameCareer);
        if (careers.size() > 0){
            Career c = careers.get(0);
            c.setNameCareer(newNameCareer);
            this.icareerRepository.save(c);
            System.out.println("Career updated JSON body");
        }
    }

    public Career updateById(Career career, Long idCareer){
        Optional<Career> c = this.icareerRepository.findById(idCareer);
        Career oldCareer = null;
        if (!c.isEmpty()){
            oldCareer = c.get();
            oldCareer.setNameCareer(career.getNameCareer());
            oldCareer = this.icareerRepository.save(oldCareer);
            System.out.println("Career update PUT");
        }
        return oldCareer;
    }

}