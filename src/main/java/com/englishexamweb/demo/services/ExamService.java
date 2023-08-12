package com.englishexamweb.demo.services;

import com.englishexamweb.demo.entities.*;
import com.englishexamweb.demo.exceptions.*;
import com.englishexamweb.demo.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

@Service
public class ExamService implements IExamService {
    private ExamRepository examRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public ExamService(ExamRepository examRepository, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Exam createRandomExamByTopic(Integer topicId, Integer questionNo) {
        List<Question> questions = questionRepository.findRandomQuestionsByTopicId(topicId, questionNo);

        Exam theExam = new Exam();
        theExam.setPercent(1.0);
        theExam.setTopicId(topicId);
        theExam.setQuestionNo(questionNo);
        theExam.setQuestions(questions);
        theExam.setTime(LocalDateTime.now());

        return examRepository.save(theExam);
    }

    @Override
    public Exam findExamById(Integer id) {
        Optional<Exam> tempExam = examRepository.findById(id);
        Exam theExam = null;
        if (tempExam.isPresent()) {
            theExam = tempExam.get();
        }
        else {
            throw new ExamNotFoundException("Exam not found - " + id);
        }
        return theExam;
    }
}
