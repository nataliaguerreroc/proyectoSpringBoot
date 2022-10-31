package com.natalia.proyectoSpringBoot.services;

import com.natalia.proyectoSpringBoot.dto.TopicDTO;
import com.natalia.proyectoSpringBoot.exceptions.CourseNotRegistered;
import com.natalia.proyectoSpringBoot.exceptions.TopicNotRegistered;
import com.natalia.proyectoSpringBoot.models.Career;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.Topic;
import com.natalia.proyectoSpringBoot.repositories.CourseRepository;
import com.natalia.proyectoSpringBoot.repositories.TopicRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;

    public TopicServiceImpl(TopicRepository topicRepository, CourseRepository courseRepository){
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
    }

    // getTopics() only shows all the topics registered and their information, like ID topic and description
    public List<Topic> getTopics(){
        List<Topic> topic = new ArrayList<>();
        this.topicRepository.findAll().forEach(topic::add);
        return topic;
    }

    public List<Topic> getTopicWithPagination(int offset, int pageSize){
        List<Topic> topic = new ArrayList<>();
        this.topicRepository.findAll(PageRequest.of(offset, pageSize)).forEach(topic::add);
        return topic;
    }


    // getTopicInfo() not only shows the topic information, but also shows the course information and the user
    // enrolled in it
    public List<TopicDTO> getTopicInfo() {
        List <Topic> data = topicRepository.getTopicsInfo();

        List<TopicDTO> topicTempList = data.stream()
                .map(t ->{
                    TopicDTO dto = new TopicDTO();
                    dto.setNameTopic(t.getNameTopic());
                    dto.setDescription(t.getDescription());
                    dto.setCourse(t.getCourse());
                    return dto;
                })
                .collect(Collectors.toList());
        return topicTempList;
    }

    public Topic add(String nameTopic, String description, String name){
        Topic topic = new Topic(nameTopic, description);
        List<Course> courses = this.courseRepository.findByName(name);
        if (courses.size() > 0){
            Course o = courses.get(0);
            topic.setCourse(o);
        }
        topic = this.topicRepository.save(topic);
        System.out.println("User added JSON body");
        return topic;

    }

    public void deleteByName(String nameTopic){
        List<Topic> topic = this.topicRepository.findByName(nameTopic);
        if (topic.size() > 0){
            Topic t = topic.get(0);
            this.topicRepository.deleteById(t.getIdTopic());
            System.out.println("User deleted JSON body");

        }
    }

    public void deleteById(Long idTopic){
        Optional<Topic> optionalTopic = this.topicRepository.findById(idTopic);
        Topic oldTopic = null;
        oldTopic = optionalTopic.orElseThrow( () -> new TopicNotRegistered(idTopic));

        this.topicRepository.deleteById(idTopic);
    }

    public void updateByName(String nameTopic, String newNameTopic,  String description, String newDescription, String name) {
        List<Topic> topic = this.topicRepository.findByName(nameTopic);
        if (topic.size() > 0) {
            Topic t = topic.get(0);
            t.setNameTopic(newNameTopic);
            t.setDescription(newDescription);
            List<Course> courses = this.courseRepository.findByName(name);
            if (courses.size() > 0) {
                Course o = courses.get(0);
                t.setCourse(o);
            }
            this.topicRepository.save(t);
            System.out.println("User updated JSON body");
        }
    }

    public Topic updateById(Topic topic, Long idTopic) {
        Optional<Topic> optionalTopic = this.topicRepository.findById(idTopic);
        Topic oldTopic = null;
        //if (optionalCourse.isPresent()) {
        oldTopic = optionalTopic.orElseThrow( () -> new TopicNotRegistered(idTopic));
        //oldCourse = optionalCourse.get();
        oldTopic.setNameTopic(topic.getNameTopic());
        List<Course> courses = this.courseRepository.findByName(topic.getCourse().getName());
        if (courses.size() > 0) {
            Course d = courses.get(0);
            oldTopic.setCourse(d);
        }
        oldTopic = this.topicRepository.save(oldTopic);
        //}
        return oldTopic;
    }


}
