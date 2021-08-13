package com.org.skillzag.assesment.service.impl;

import com.org.skillzag.assesment.service.UserCompleteTestService;
import com.org.skillzag.assesment.domain.UserCompleteTest;
import com.org.skillzag.assesment.repository.UserCompleteTestRepository;
import com.org.skillzag.assesment.service.dto.UserCompleteTestDTO;
import com.org.skillzag.assesment.service.mapper.UserCompleteTestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserCompleteTest}.
 */
@Service
@Transactional
public class UserCompleteTestServiceImpl implements UserCompleteTestService {

    private final Logger log = LoggerFactory.getLogger(UserCompleteTestServiceImpl.class);

    private final UserCompleteTestRepository userCompleteTestRepository;

    private final UserCompleteTestMapper userCompleteTestMapper;

    public UserCompleteTestServiceImpl(UserCompleteTestRepository userCompleteTestRepository, UserCompleteTestMapper userCompleteTestMapper) {
        this.userCompleteTestRepository = userCompleteTestRepository;
        this.userCompleteTestMapper = userCompleteTestMapper;
    }

    @Override
    public UserCompleteTestDTO save(UserCompleteTestDTO userCompleteTestDTO) {
        log.debug("Request to save UserCompleteTest : {}", userCompleteTestDTO);
        UserCompleteTest userCompleteTest = userCompleteTestMapper.toEntity(userCompleteTestDTO);
        userCompleteTest = userCompleteTestRepository.save(userCompleteTest);
        return userCompleteTestMapper.toDto(userCompleteTest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserCompleteTestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserCompleteTests");
        return userCompleteTestRepository.findAll(pageable)
            .map(userCompleteTestMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserCompleteTestDTO> findOne(Long id) {
        log.debug("Request to get UserCompleteTest : {}", id);
        return userCompleteTestRepository.findById(id)
            .map(userCompleteTestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserCompleteTest : {}", id);
        userCompleteTestRepository.deleteById(id);
    }
}
