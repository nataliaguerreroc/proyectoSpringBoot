package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.services.CareerServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CareerController {
    private final CareerServiceImpl careerServiceImpl;

    public CareerController(CareerServiceImpl careerServiceImpl){
        this.careerServiceImpl = careerServiceImpl;
    }

    @GetMapping("/careers")
    public List<Career> getCareers(){
        return this.careerServiceImpl.getCareers();
    }

    @GetMapping("/careers/nameCareers")
    public List<String> getCareersNames(){
        return this.careerServiceImpl.getNames();
    }

    @PostMapping(value = "/careers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Career createCareer(@RequestBody Career career){
        return this.careerServiceImpl.add(career);
    }

    @PutMapping(value = "/careers/{idCareer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Career updateCareer(@RequestBody Career career,@PathVariable Long idCareer){
        return this.careerServiceImpl.updateById(career,idCareer);
    }

    @DeleteMapping(value = "/careers/{idCareer}")
    public void deleteCareer(@PathVariable Long idCareer){
        this.careerServiceImpl.deleteById(idCareer);
    }
}