package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.exceptions.CareerNotRegistered;
import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.repositories.CareerRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CareerService implements CareerServiceImpl {
    private final CareerRepositoryImpl careerRepositoryImpl;

    public CareerService(CareerRepositoryImpl careerRepositoryImpl) {
        this.careerRepositoryImpl = careerRepositoryImpl;
    }


    // getNames() is used to show all the careers that are registered (only the name of these)
    public List<String> getNames(){
        return this.careerRepositoryImpl.getNames();
    }

    // getCareers() shows the career information, includes the courses and users information that are enrolled in a career
    public List<Career> getCareers(){
        List<Career> careers = new ArrayList<>();
        this.careerRepositoryImpl.findAll().forEach(careers::add);
        return careers;
    }

    public Career add(Career career){
        return this.careerRepositoryImpl.save(career);
    }

    public void deleteByName(String nameCareer){
        List<Career> careers = this.careerRepositoryImpl.findByName(nameCareer);
        if (careers.size() > 0){
            Career c = careers.get(0);
            this.careerRepositoryImpl.deleteById(c.getIdCareer());
            System.out.println("Career deleted JSON body");
        }
    }

    public void deleteById(Long idCareer){
        Optional<Career> optionalCareer = this.careerRepositoryImpl.findById(idCareer);
        Career oldCareer = null;
        oldCareer = optionalCareer.orElseThrow( () -> new CareerNotRegistered(idCareer));

        this.careerRepositoryImpl.deleteById(idCareer);
    }

    public void updateByName(String nameCareer, String newNameCareer){
        List<Career> careers = this.careerRepositoryImpl.findByName(nameCareer);
        if (careers.size() > 0){
            Career c = careers.get(0);
            c.setNameCareer(newNameCareer);
            this.careerRepositoryImpl.save(c);
            System.out.println("Career updated JSON body");
        }
    }

    public Career updateById(Career career, Long idCareer){
        Optional<Career> optionalCareer = this.careerRepositoryImpl.findById(idCareer);
        Career oldCareer = null;
        //if (!optionalCareer.isEmpty()){
            oldCareer = optionalCareer.orElseThrow( () -> new CareerNotRegistered(idCareer));
            //oldCareer = optionalCareer.get();
            oldCareer.setNameCareer(career.getNameCareer());
            oldCareer = this.careerRepositoryImpl.save(oldCareer);
            System.out.println("Career update PUT");
        //}
        return oldCareer;
    }

}