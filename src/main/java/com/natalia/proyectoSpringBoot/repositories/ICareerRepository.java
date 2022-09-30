package com.natalia.proyectoSpringBoot.repositories;

import com.natalia.proyectoSpringBoot.models.Career;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICareerRepository extends CrudRepository<Career, Long> {
}
