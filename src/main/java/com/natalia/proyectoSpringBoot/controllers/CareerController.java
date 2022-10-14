package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.services.ICareerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CareerController {
    private final ICareerService icareerService;

    public CareerController(ICareerService icareerService){
        this.icareerService = icareerService;
    }

    @GetMapping("/careers")
    public List<Career> getCareers(){
        return this.icareerService.getCareers();
    }

    @GetMapping("/careers/nameCareers")
    public List<String> getCareersNames(){
        return this.icareerService.getNames();
    }

    @PostMapping(value = "/careers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Career createCareer(@RequestBody Career career){
        return this.icareerService.add(career);
    }

    @PutMapping(value = "/careers/{idCareer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Career updateCareer(@RequestBody Career career,@PathVariable Long idCareer){
        return this.icareerService.updateById(career,idCareer);
    }

    @DeleteMapping(value = "/careers/{idCareer}")
    public void deleteCareer(@PathVariable Long idCareer){
        this.icareerService.deleteById(idCareer);
    }
}