package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.services.CareerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/careers")
public class CareerController {
    private final CareerService careerService;

    public CareerController(CareerService careerService){
        this.careerService = careerService;
    }

    @GetMapping()
    public List<Career> getCareers(){
        return this.careerService.getCareers();
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public List<Career> getNamesWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        return this.careerService.getNamesWithPagination(offset, pageSize);
    }

    @GetMapping("/{nameCareers}")
    public List<String> getCareersNames(){
        return this.careerService.getNames();
    }

    @PostMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Career createCareer(@RequestBody Career career){
        return this.careerService.add(career);
    }

    @PutMapping(value = "/{idCareer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Career updateCareer(@RequestBody Career career,@PathVariable Long idCareer){
        return this.careerService.updateById(career,idCareer);
    }

    @DeleteMapping(value = "/{idCareer}")
    public void deleteCareer(@PathVariable Long idCareer){
        this.careerService.deleteById(idCareer);
    }
}