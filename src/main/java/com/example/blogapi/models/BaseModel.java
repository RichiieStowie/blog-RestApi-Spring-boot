package com.example.blogapi.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateCreated;
    @DateTimeFormat(pattern = "hh-mm-ss")
    private LocalTime timeCreated;
    private LocalDateTime localDateTime;
}
