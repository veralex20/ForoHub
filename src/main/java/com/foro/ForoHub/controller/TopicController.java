package com.foro.ForoHub.controller;

import com.foro.ForoHub.entity.Topic;
import com.foro.ForoHub.repository.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicRepository repository;

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody @Valid Topic topic) {
        if (repository.existsByTitleAndMessage(topic.getTitle(), topic.getMessage())) {
            return ResponseEntity.badRequest().body("Topic already exists");
        }
        Topic savedTopic = repository.save(topic);
        return ResponseEntity.ok(savedTopic);
    }

    @GetMapping
    public List<Topic> listTopics() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopic(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody @Valid Topic updatedTopic) {
        return repository.findById(id).map(topic -> {
            topic.setTitle(updatedTopic.getTitle());
            topic.setMessage(updatedTopic.getMessage());
            topic.setStatus(updatedTopic.getStatus());
            topic.setAuthor(updatedTopic.getAuthor());
            topic.setCourse(updatedTopic.getCourse());
            repository.save(topic);
            return ResponseEntity.ok(topic);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
