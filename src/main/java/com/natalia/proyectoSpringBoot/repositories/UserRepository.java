package com.natalia.proyectoSpringBoot.repositories;

import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    @Query("Select u from User u left join Career c on c = u.career")
    List<User> getUsersInfo();

    @Query("Select u from User u where u.name = :name") //We can use also native SQL to define our query. All we have to do is set the value of the nativeQuery attribute to true and define the native SQL query in the value attribute of the annotation:
    List<User> findByName(@Param("name") String name);
}
