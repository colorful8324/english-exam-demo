package com.englishexamweb.demo.controllers;

import com.englishexamweb.demo.entities.*;
import com.englishexamweb.demo.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topics")
public class TopicRestController {
    private final TopicService topicService;

    @GetMapping
    public List<Topic> findAllTopics() {
        return topicService.findAllTopics();
    }

    @GetMapping("/{topicId}/questions")
    public List<Question> findAllQuestions(@PathVariable Integer topicId) {
        return topicService.findAllQuestionsById(topicId);
    }

    @GetMapping("{id}")
    public Topic findTopicById(@PathVariable Integer id) {
        return topicService.findTopicById(id);
    }
}
