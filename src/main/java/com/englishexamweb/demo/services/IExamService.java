package com.englishexamweb.demo.services;

import com.englishexamweb.demo.entities.*;

import java.util.*;

public interface IExamService {
    Exam createRandomExamByTopic(Integer topicId, Integer questionNo);
    Exam findExamById(Integer id);
}
