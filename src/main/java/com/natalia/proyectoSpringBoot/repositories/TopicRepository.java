package com.natalia.proyectoSpringBoot.repositories;

import com.natalia.proyectoSpringBoot.models.Topic;
import com.natalia.proyectoSpringBoot.services.TopicService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository <Topic, Long>, PagingAndSortingRepository<Topic, Long> {
    @Query("Select t from Topic t left join Course o on o = t.course")
    List<Topic> getTopicsInfo();

    @Query("Select t from Topic t where t.nameTopic = :nameTopic")
    List<Topic> findByName(@Param("nameTopic") String nameTopic);
}
