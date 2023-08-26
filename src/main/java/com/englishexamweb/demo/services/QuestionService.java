package com.englishexamweb.demo.services;

import com.englishexamweb.demo.entities.*;
import com.englishexamweb.demo.exceptions.*;
import com.englishexamweb.demo.repositories.*;
import com.englishexamweb.demo.util.CsvUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    private final QuestionRepository questionRepository;
    private final CsvUtils csvUtils;

    @Override
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }
    @Override
    public Question findById(Integer id) {
        Optional<Question> result = questionRepository.findById(id);
        Question theQuestion = null;
        if (result.isPresent()) {
            theQuestion = result.get();
        }
        else {
            throw new QuestionNotFoundException("Question not found - " + id);
        }
        return theQuestion;
    }

    @Override
    public List<Question> findQuestionsByTopicId(Integer topicId) {
        return questionRepository.findByTopicId(topicId);
    }

    @Override
    public Question saveQuestion(Question theQuestion) {
        return questionRepository.save(theQuestion);
    }

    @Override
    public void deleteQuestionById(Integer id) {
        questionRepository.deleteById(id);
    }

    public List<Question> saveFromCSV(InputStream is) {
        List<Question> questions = csvUtils.csvToQuestion(is);
        questionRepository.saveAll(questions);

        return questions;
    }
}
