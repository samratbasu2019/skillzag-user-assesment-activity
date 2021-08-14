package com.org.skillzag.assesment.service.impl;

import com.org.skillzag.assesment.domain.UserInCompleteTest;
import com.org.skillzag.assesment.repository.UserInCompleteTestRepository;
import com.org.skillzag.assesment.service.UserInCompleteTestService;
import com.org.skillzag.assesment.service.dto.UserInCompleteTestDTO;
import com.org.skillzag.assesment.service.mapper.UserInCompleteTestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserInCompleteTest}.
 */
@Service
@Transactional
public class UserInCompleteTestServiceImpl implements UserInCompleteTestService {

    private final Logger log = LoggerFactory.getLogger(UserInCompleteTestServiceImpl.class);

    private final UserInCompleteTestRepository userInCompleteTestRepository;

    private final UserInCompleteTestMapper userInCompleteTestMapper;

    public UserInCompleteTestServiceImpl(UserInCompleteTestRepository userInCompleteTestRepository, UserInCompleteTestMapper userInCompleteTestMapper) {
        this.userInCompleteTestRepository = userInCompleteTestRepository;
        this.userInCompleteTestMapper = userInCompleteTestMapper;
    }

    @Override
    public UserInCompleteTestDTO save(UserInCompleteTestDTO userInCompleteTestDTO) {
        log.debug("Request to save UserInCompleteTest : {}", userInCompleteTestDTO);
        UserInCompleteTest userInCompleteTest = userInCompleteTestMapper.toEntity(userInCompleteTestDTO);
        userInCompleteTest = userInCompleteTestRepository.save(userInCompleteTest);
        return userInCompleteTestMapper.toDto(userInCompleteTest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserInCompleteTestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserInCompleteTests");
        return userInCompleteTestRepository.findAll(pageable)
            .map(userInCompleteTestMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserInCompleteTestDTO> findOne(Long id) {
        log.debug("Request to get UserInCompleteTest : {}", id);
        return userInCompleteTestRepository.findById(id)
            .map(userInCompleteTestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserInCompleteTest : {}", id);
        userInCompleteTestRepository.deleteById(id);
    }
}
