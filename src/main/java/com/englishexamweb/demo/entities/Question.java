package com.englishexamweb.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qu_id")
    private Integer id;

    @Column(name = "qu_content", length = 500)
    private String content;

    @Column(name = "qu_option1")
    private String option1;

    @Column(name = "qu_option2")
    private String option2;

    @Column(name = "qu_option3")
    private String option3;

    @Column(name = "qu_option4")
    private String option4;

    @Column(name = "qu_answer")
    private Integer answer;

    @Column(name = "qu_explain")
    private String explain;

    @Column(name = "qu_type")
    private Integer type;

    @JoinColumn(name = "tp_id")
    @ManyToOne
    private Topic topic;

    @Column(name = "difficult_level")
    private Integer level;

    @Transient
    public String getCorrectAnswer() {
        if (answer == 1) {
            return option1;
        } else if (answer == 2) {
            return option2;
        } else if (answer == 3) {
            return option3;
        } else if (answer == 4) {
            return option4;
        }

        return null;
    }

}
