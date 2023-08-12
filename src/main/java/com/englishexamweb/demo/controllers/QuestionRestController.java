package com.englishexamweb.demo.controllers;

import com.englishexamweb.demo.entities.*;
import com.englishexamweb.demo.exceptions.*;
import com.englishexamweb.demo.services.*;
import com.englishexamweb.demo.util.CsvUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/questions")
public class QuestionRestController {
    private final QuestionService questionService;
    private final CsvUtils csvUtils;

    @Autowired
    public QuestionRestController(QuestionService questionService, CsvUtils csvUtils) {
        this.questionService = questionService;
        this.csvUtils = csvUtils;
    }

    @GetMapping
    public List<Question> findAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping(("/{id}"))
    public Question findQuestionById(@PathVariable Integer id) {
        Question tempQuestion = questionService.findById(id);
        if (tempQuestion == null) {
            // just for clear context
            // this maybe unnecessary cause we already had this checked at service layer
            throw new QuestionNotFoundException("Question id not found - " + id);
        }
        return tempQuestion;
    }

    @GetMapping("/topicId/{theTopicId}")
    public List<Question> findQuestionsByTopicId(@PathVariable Integer theTopicId) {
        return questionService.findQuestionsByTopicId(theTopicId);
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question theQuestion) {
        theQuestion.setId(0);
        return questionService.saveQuestion(theQuestion);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Integer id, @RequestBody Question theQuestion) {
        Question tempQuestion = questionService.findById(id);
        theQuestion.setId(id);
        return questionService.saveQuestion(theQuestion);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Integer id) {
        Question tempQuestion = questionService.findById(id);
        if (tempQuestion == null) {
            // just for clear context
            // this maybe unnecessary cause we already had this checked at service layer
            throw new QuestionNotFoundException("Question id not found - " + id);
        }
        questionService.deleteQuestionById(id);
        return "Deleted question id - " + id;
    }

    @PostMapping("/csvUpload")
    public List<Question> csvUploadQuestion(@RequestParam MultipartFile file) {
        try {
            if (csvUtils.hasCsvFormat(file)) {
                InputStream is = file.getInputStream();
                return questionService.saveFromCSV(is);
            }
            else {
                throw new CSVException("The file is not an .csv file");
            }
        }
        catch (IOException exc) {
            throw new CSVException("Failed to parse .csv file");
        }
    }
}
