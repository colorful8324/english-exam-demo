package com.englishexamweb.demo.controllers;

import com.englishexamweb.demo.entities.*;
import com.englishexamweb.demo.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exams")
public class ExamRestController {
    private ExamService examService;

    @Autowired
    public ExamRestController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/{id}")
    public Exam findExamById(@PathVariable Integer id) {
        return examService.findExamById(id);
    }

    @PostMapping("/random")
    public Exam createRandomExamByTopic(@RequestParam Integer topicId, @RequestParam int questionNo) {
        Exam theExam = examService.createRandomExamByTopic(topicId, questionNo);
        return theExam;
    }
}
