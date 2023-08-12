package com.englishexamweb.demo.services;

import com.englishexamweb.demo.entities.*;

import java.util.*;

public interface IQuestionService {
    public List<Question> findAllQuestions();
    Question findById(Integer id);
    List<Question> findQuestionsByTopicId(Integer topicId);
    Question saveQuestion(Question theQuestion);
    void deleteQuestionById(Integer id);
}
