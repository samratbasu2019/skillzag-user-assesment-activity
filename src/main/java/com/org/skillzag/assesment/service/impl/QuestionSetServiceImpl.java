package com.org.skillzag.assesment.service.impl;

import com.org.skillzag.assesment.domain.QuestionSet;
import com.org.skillzag.assesment.repository.QuestionSetRepository;
import com.org.skillzag.assesment.service.QuestionSetService;
import com.org.skillzag.assesment.service.dto.QuestionSetDTO;
import com.org.skillzag.assesment.service.mapper.QuestionSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QuestionSet}.
 */
@Service
@Transactional
public class QuestionSetServiceImpl implements QuestionSetService {

    private final Logger log = LoggerFactory.getLogger(QuestionSetServiceImpl.class);

    private final QuestionSetRepository questionSetRepository;

    private final QuestionSetMapper questionSetMapper;

    public QuestionSetServiceImpl(QuestionSetRepository questionSetRepository, QuestionSetMapper questionSetMapper) {
        this.questionSetRepository = questionSetRepository;
        this.questionSetMapper = questionSetMapper;
    }

    @Override
    public QuestionSetDTO save(QuestionSetDTO questionSetDTO) {
        log.debug("Request to save QuestionSet : {}", questionSetDTO);
        QuestionSet questionSet = questionSetMapper.toEntity(questionSetDTO);
        questionSet = questionSetRepository.save(questionSet);
        return questionSetMapper.toDto(questionSet);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionSetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuestionSets");
        return questionSetRepository.findAll(pageable)
            .map(questionSetMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionSetDTO> findOne(Long id) {
        log.debug("Request to get QuestionSet : {}", id);
        return questionSetRepository.findById(id)
            .map(questionSetMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuestionSet : {}", id);
        questionSetRepository.deleteById(id);
    }
}
