package com.englishexamweb.demo.services;

import com.englishexamweb.demo.entities.*;
import com.englishexamweb.demo.exceptions.*;
import com.englishexamweb.demo.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TopicService implements ITopicService {
    private TopicRepository topicRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository, QuestionRepository questionRepository) {
        this.topicRepository = topicRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findTopicById(Integer id) {
        Optional<Topic> tempTopic = topicRepository.findById(id);
        Topic theTopic = null;
        if ((tempTopic.isPresent())) {
            theTopic = tempTopic.get();
        }
        else {
            throw new TopicNotFoundException("Topic not found - " + id);
        }
        return theTopic;
    }

    @Override
    public List<Question> findAllQuestionsById(Integer topicId) {
        return questionRepository.findByTopicId(topicId);
    }


}
