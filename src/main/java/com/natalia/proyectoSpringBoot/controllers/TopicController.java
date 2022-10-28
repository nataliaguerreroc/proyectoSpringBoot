package com.natalia.proyectoSpringBoot.controllers;

import com.natalia.proyectoSpringBoot.dto.CourseDTO;
import com.natalia.proyectoSpringBoot.dto.TopicDTO;
import com.natalia.proyectoSpringBoot.models.Course;
import com.natalia.proyectoSpringBoot.models.Topic;
import com.natalia.proyectoSpringBoot.services.TopicService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService){
        this.topicService = topicService;
    }

    @GetMapping()
    public List<Topic> getTopics(){
        return this.topicService.getTopics();
    }

    @GetMapping("/{info}")
    public List <TopicDTO> getTopicInfo(){
        return this.topicService.getTopicInfo();
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public List<Topic> getTopicWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        return this.topicService.getTopicWithPagination(offset, pageSize);
    }

    @PostMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Topic createTopic(@RequestBody Map<String, String> json){
        return this.topicService.add(json.get("nameTopic"),json.get("description"), json.get("course"));
    }

    @PutMapping(value = "/{idTopic}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Topic updateTopic(@RequestBody Topic topic, @PathVariable Long idTopic){
        return this.topicService.updateById(topic, idTopic);
    }

    @DeleteMapping(value = "/{idTopic}")
    public void deleteTopic(@PathVariable Long idTopic){
        this.topicService.deleteById(idTopic);

    }

}
