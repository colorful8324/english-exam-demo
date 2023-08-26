package com.englishexamweb.demo.controllers;

import com.englishexamweb.demo.entities.*;
import com.englishexamweb.demo.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamRestController {
    private final ExamService examService;

    @GetMapping("/{id}")
    public Exam findExamById(@PathVariable Integer id) {
        return examService.findExamById(id);
    }

//    @PostMapping("/random")
//    public Exam createRandomExamByTopic(@RequestParam Integer topicId, @RequestParam int questionNo) {
//        Exam theExam = examService.createRandomExamByTopic(topicId, questionNo);
//        return theExam;
//    }
}
