package com.natalia.proyectoSpringBoot.repositories;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository <Course, Long>, PagingAndSortingRepository<Course, Long> {
    @Query("Select o from Course o left join Career c on c = o.career")
    List<Course> getCoursesInfo();

    @Query("Select o from Course o where o.name = :name")
    List<Course> findByName(@Param("name") String name);
}
