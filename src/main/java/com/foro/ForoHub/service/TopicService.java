package com.foro.ForoHub.service;

import com.foro.ForoHub.entity.Course;
import com.foro.ForoHub.entity.Topic;
import com.foro.ForoHub.entity.User;
import com.foro.ForoHub.exception.ResourceNotFoundException;
import com.foro.ForoHub.repository.CourseRepository;
import com.foro.ForoHub.repository.TopicRepository;
import com.foro.ForoHub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    // Crear un nuevo tópico
    public Topic createTopic(Topic topic) {
        // Lógica para verificar si ya existe un tópico con el mismo título y mensaje
        Optional<Topic> existingTopic = topicRepository.findByTitleAndMessage(topic.getTitle(), topic.getMessage());
        if (existingTopic.isPresent()) {
            throw new RuntimeException("Topic already exists with the same title and message.");
        }

        // Validar que el autor existe
        Optional<User> author = userRepository.findById(topic.getAuthor().getId());
        if (author.isEmpty()) {
            throw new RuntimeException("Author not found");
        }
        topic.setAuthor(author.get());

        // Validar que el curso existe
        Optional<Course> course = courseRepository.findById(topic.getCourse().getId());
        if (course.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        topic.setCourse(course.get());

        // Establecer fecha de creación y estado por defecto si no están presentes
        topic.setCreationDate(LocalDateTime.now());
        if (topic.getStatus() == null) {
            topic.setStatus("ACTIVO");
        }
        // Guardar el tópico en la base de datos
        return topicRepository.save(topic);
    }

    // Obtener todos los tópicos
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    // Obtener un tópico por ID
    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    // Actualizar un tópico
    public Topic updateTopic(Long id, Topic topic) {
        Optional<Topic> existingTopic = topicRepository.findById(id);
        if (existingTopic.isPresent()) {
            Topic updatedTopic = existingTopic.get();
            updatedTopic.setTitle(topic.getTitle());
            updatedTopic.setMessage(topic.getMessage());
//            updatedTopic.setCreationDate(topic.getCreationDate());
            updatedTopic.setStatus(topic.getStatus());
            updatedTopic.setAuthor(topic.getAuthor());
            updatedTopic.setCourse(topic.getCourse());
            return topicRepository.save(updatedTopic);
        } else {
            throw new RuntimeException("Topic not found with id " + id);
        }
    }

    // Eliminar un tópico
    public void deleteTopic(Long id) {
        Optional<Topic> existingTopic = topicRepository.findById(id);
        if (existingTopic.isEmpty()) {
            throw new ResourceNotFoundException("Topic not found with id " + id);
        }
        topicRepository.deleteById(id);
    }

}
