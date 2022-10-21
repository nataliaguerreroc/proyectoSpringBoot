package com.natalia.proyectoSpringBoot.repositories;

import com.natalia.proyectoSpringBoot.models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryImpl extends CrudRepository <Course, Long> {
    @Query("Select o from Course o left join Career c on c = o.career")
    List<Course> getCoursesInfo();

    @Query("Select o from Course o where o.name = :name")
    List<Course> findByName(@Param("name") String name);
}
