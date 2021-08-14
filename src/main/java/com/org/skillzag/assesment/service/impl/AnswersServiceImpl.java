package com.org.skillzag.assesment.service.impl;

import com.org.skillzag.assesment.domain.Answers;
import com.org.skillzag.assesment.repository.AnswersRepository;
import com.org.skillzag.assesment.service.AnswersService;
import com.org.skillzag.assesment.service.dto.AnswersDTO;
import com.org.skillzag.assesment.service.mapper.AnswersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Answers}.
 */
@Service
@Transactional
public class AnswersServiceImpl implements AnswersService {

    private final Logger log = LoggerFactory.getLogger(AnswersServiceImpl.class);

    private final AnswersRepository answersRepository;

    private final AnswersMapper answersMapper;

    public AnswersServiceImpl(AnswersRepository answersRepository, AnswersMapper answersMapper) {
        this.answersRepository = answersRepository;
        this.answersMapper = answersMapper;
    }

    @Override
    public AnswersDTO save(AnswersDTO answersDTO) {
        log.debug("Request to save Answers : {}", answersDTO);
        Answers answers = answersMapper.toEntity(answersDTO);
        answers = answersRepository.save(answers);
        return answersMapper.toDto(answers);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnswersDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Answers");
        return answersRepository.findAll(pageable)
            .map(answersMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AnswersDTO> findOne(Long id) {
        log.debug("Request to get Answers : {}", id);
        return answersRepository.findById(id)
            .map(answersMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Answers : {}", id);
        answersRepository.deleteById(id);
    }
}
