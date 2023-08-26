package com.englishexamweb.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_id")
    private Integer id;

    @Column(name = "ex_name")
    private String name;

    @Column(name = "ex_question_no")
    private Integer questionNo;

    @Column(name = "ex_time", columnDefinition = "DATETIME")
    private Date time;

    @Column(name = "ex_type")
    private Integer type;

}
