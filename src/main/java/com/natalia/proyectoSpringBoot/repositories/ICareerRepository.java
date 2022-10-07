package com.natalia.proyectoSpringBoot.repositories;

import com.natalia.proyectoSpringBoot.models.Career;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Repository
public interface ICareerRepository extends CrudRepository<Career, Long> {
    @Query("SELECT nameCareer from Career")//si no se le pone nativeQuery en true se esta usando JPQL
    List<String> getNames();

    @Query("Select c from Career c where c.nameCareer = :nameCareer") //We can use also native SQL to define our query. All we have to do is set the value of the nativeQuery attribute to true and define the native SQL query in the value attribute of the annotation:
    List<Career> findByName(@Param("nameCareer") String nameCareer);

}
