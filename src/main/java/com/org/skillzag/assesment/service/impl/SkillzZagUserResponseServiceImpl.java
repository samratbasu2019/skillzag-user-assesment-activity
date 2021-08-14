package com.org.skillzag.assesment.service.impl;

import com.org.skillzag.assesment.service.SkillzZagUserResponseService;
import com.org.skillzag.assesment.domain.SkillzZagUserResponse;
import com.org.skillzag.assesment.repository.SkillzZagUserResponseRepository;
import com.org.skillzag.assesment.service.dto.SkillzZagUserResponseDTO;
import com.org.skillzag.assesment.service.mapper.SkillzZagUserResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SkillzZagUserResponse}.
 */
@Service
@Transactional
public class SkillzZagUserResponseServiceImpl implements SkillzZagUserResponseService {

    private final Logger log = LoggerFactory.getLogger(SkillzZagUserResponseServiceImpl.class);

    private final SkillzZagUserResponseRepository skillzZagUserResponseRepository;

    private final SkillzZagUserResponseMapper skillzZagUserResponseMapper;

    public SkillzZagUserResponseServiceImpl(SkillzZagUserResponseRepository skillzZagUserResponseRepository, SkillzZagUserResponseMapper skillzZagUserResponseMapper) {
        this.skillzZagUserResponseRepository = skillzZagUserResponseRepository;
        this.skillzZagUserResponseMapper = skillzZagUserResponseMapper;
    }

    @Override
    public SkillzZagUserResponseDTO save(SkillzZagUserResponseDTO skillzZagUserResponseDTO) {
        log.debug("Request to save SkillzZagUserResponse : {}", skillzZagUserResponseDTO);
        SkillzZagUserResponse skillzZagUserResponse = skillzZagUserResponseMapper.toEntity(skillzZagUserResponseDTO);
        skillzZagUserResponse = skillzZagUserResponseRepository.save(skillzZagUserResponse);
        return skillzZagUserResponseMapper.toDto(skillzZagUserResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SkillzZagUserResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SkillzZagUserResponses");
        return skillzZagUserResponseRepository.findAll(pageable)
            .map(skillzZagUserResponseMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SkillzZagUserResponseDTO> findOne(Long id) {
        log.debug("Request to get SkillzZagUserResponse : {}", id);
        return skillzZagUserResponseRepository.findById(id)
            .map(skillzZagUserResponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SkillzZagUserResponse : {}", id);
        skillzZagUserResponseRepository.deleteById(id);
    }
}
