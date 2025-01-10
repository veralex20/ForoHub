package com.foro.ForoHub.repository;


import com.foro.ForoHub.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long>{
    boolean existsByTitleAndMessage(String title, String message);
}
