package com.foro.ForoHub.controller;

import com.foro.ForoHub.entity.Topic;
import com.foro.ForoHub.repository.CourseRepository;
import com.foro.ForoHub.repository.TopicRepository;
import com.foro.ForoHub.repository.UserRepository;
import com.foro.ForoHub.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;


@Autowired
    public TopicController(TopicService topicService, UserRepository userRepository, CourseRepository courseRepository) {
        this.topicService = topicService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    // Crear un nuevo tópico
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topic createTopic(@RequestBody @Valid Topic topic) {
        return topicService.createTopic(topic);
    }

    // Obtener todos los tópicos
    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    // Obtener un tópico por ID
    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable Long id) {
        return topicService.getTopicById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found with id " + id));
    }

    // Actualizar un tópico
    @PutMapping("/{id}")
    public Topic updateTopic(@PathVariable Long id, @RequestBody @Valid Topic topic) {
        return topicService.updateTopic(id, topic);
    }

    // Eliminar un tópico
    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Map<String, String>> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "El tópico fue eliminado exitosamente");
        return ResponseEntity.ok(response);
    }
}
