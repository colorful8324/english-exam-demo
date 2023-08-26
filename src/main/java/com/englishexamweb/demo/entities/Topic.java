package com.englishexamweb.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "topics")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tp_id")
    private Integer id;

    @Column(name = "tp_name")
    private String name;

    @Column(name = "tp_description")
    private String description;
}
