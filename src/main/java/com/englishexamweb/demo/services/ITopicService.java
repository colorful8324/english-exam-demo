package com.englishexamweb.demo.services;

import com.englishexamweb.demo.entities.*;

import java.util.*;

public interface ITopicService {
    List<Topic> findAllTopics();
    Topic findTopicById(Integer topicId);
    List<Question> findAllQuestionsById(Integer id);
}
