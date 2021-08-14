package com.org.skillzag.assesment.service.impl;

import com.org.skillzag.assesment.domain.Questions;
import com.org.skillzag.assesment.repository.CRUDQuestionAnswerRepository;
import com.org.skillzag.assesment.service.mapper.QuestionAnswersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service Implementation for managing {@link Questions}.
 */
@Service
@Transactional
public class QuestionAnswersServiceImpl implements QuestionAnswersService {

    private final Logger log = LoggerFactory.getLogger(QuestionAnswersServiceImpl.class);


    private final CRUDQuestionAnswerRepository crudQuestionAnswerRepository;

    public QuestionAnswersServiceImpl(CRUDQuestionAnswerRepository crudQuestionAnswerRepository) {
        this.crudQuestionAnswerRepository = crudQuestionAnswerRepository;
    }
    @Override
    public List<Map<String, Object>> findQuestionAnswer(Long id) {
        log.debug("event=findQuestionAnswer Request to get Question Answers : {}", id);
        return crudQuestionAnswerRepository.findQuestionAnswers(id);
    }
}
