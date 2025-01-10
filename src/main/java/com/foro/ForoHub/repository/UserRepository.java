package com.foro.ForoHub.repository;

import com.foro.ForoHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
