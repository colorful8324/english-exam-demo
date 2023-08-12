package com.englishexamweb.demo.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * A question without correct answer, used for fetching to client
 * @author Khanh Nguyen
 */
@Data
@NoArgsConstructor
public class QuestionWrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qu_id")
    private Integer id;
    @Column(name = "qu_content")
    private String questionContent;
    @Column(name = "qu_option1")
    private String option1;
    @Column(name = "qu_option2")
    private String option2;
    @Column(name = "qu_option3")
    private String option3;
    @Column(name = "qu_option4")
    private String option4;
    @Column(name = "qu_explain")
    private String explain;
    @Column(name = "qu_type")
    private Integer type;
    @Column(name = "tp_id")
    private Integer topicId;
    @Column(name = "lv_id")
    private Integer level;
}