package com.foro.ForoHub.repository;


import com.foro.ForoHub.entity.AuthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthDataRepository extends JpaRepository<AuthData, Long>{
    Optional<AuthData> findByUsername(String username);
}
