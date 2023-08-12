package com.englishexamweb.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_id")
    private Integer id;
    @Column(name = "ex_percent")
    private Double percent;
    @Column(name = "tp_id")
    private Integer topicId;
    @Column(name = "ex_question_no")
    private Integer questionNo;
    @Column(name = "ex_time")
    private LocalDateTime time;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "EX_ID"),
            inverseJoinColumns = @JoinColumn(name = "QU_ID"))
    List<Question> questions;

    public Exam(Double percent, Integer topicId, Integer questionNo, LocalDateTime time, List<Question> questions) {
        this.percent = percent;
        this.topicId = topicId;
        this.questionNo = questionNo;
        this.time = time;
        this.questions = questions;
    }
}
