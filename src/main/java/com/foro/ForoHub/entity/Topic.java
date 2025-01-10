package com.foro.ForoHub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDate creationDate = LocalDate.now();

    @Column(nullable = false)
    private String status = "OPEN";

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String course;

}
