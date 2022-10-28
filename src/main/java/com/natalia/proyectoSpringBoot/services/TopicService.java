package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.dto.CourseDTO;
import com.natalia.proyectoSpringBoot.dto.TopicDTO;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getTopics();
    List<Topic> getTopicWithPagination(int offset, int pageSize);
    List <TopicDTO> getTopicInfo();
    Topic add(String nameTopic, String description, String name);
    void deleteByName(String nameTopic);
    void deleteById(Long idTopic);
    void updateByName(String nameTopic, String newNameTopic,  String description, String newDescription, String name);
    Topic updateById(Topic topic, Long idTopic);



}
