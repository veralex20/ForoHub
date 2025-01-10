package com.foro.ForoHub.repository;


import com.foro.ForoHub.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long>{

    // Buscar un tópico por título y mensaje (para evitar duplicados)
    Optional<Topic> findByTitleAndMessage(String title, String message);

    boolean existsByTitleAndMessage(String title, String message);
}
