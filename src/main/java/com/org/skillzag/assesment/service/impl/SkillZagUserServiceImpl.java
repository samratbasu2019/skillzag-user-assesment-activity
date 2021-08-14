package com.org.skillzag.assesment.service.impl;

import com.org.skillzag.assesment.domain.SkillZagUser;
import com.org.skillzag.assesment.repository.SkillZagUserRepository;
import com.org.skillzag.assesment.service.SkillZagUserService;
import com.org.skillzag.assesment.service.dto.SkillZagUserDTO;
import com.org.skillzag.assesment.service.mapper.SkillZagUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SkillZagUser}.
 */
@Service
@Transactional
public class SkillZagUserServiceImpl implements SkillZagUserService {

    private final Logger log = LoggerFactory.getLogger(SkillZagUserServiceImpl.class);

    private final SkillZagUserRepository skillZagUserRepository;

    private final SkillZagUserMapper skillZagUserMapper;

    public SkillZagUserServiceImpl(SkillZagUserRepository skillZagUserRepository, SkillZagUserMapper skillZagUserMapper) {
        this.skillZagUserRepository = skillZagUserRepository;
        this.skillZagUserMapper = skillZagUserMapper;
    }

    @Override
    public SkillZagUserDTO save(SkillZagUserDTO skillZagUserDTO) {
        log.debug("Request to save SkillZagUser : {}", skillZagUserDTO);
        SkillZagUser skillZagUser = skillZagUserMapper.toEntity(skillZagUserDTO);
        skillZagUser = skillZagUserRepository.save(skillZagUser);
        return skillZagUserMapper.toDto(skillZagUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SkillZagUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SkillZagUsers");
        return skillZagUserRepository.findAll(pageable)
            .map(skillZagUserMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SkillZagUserDTO> findOne(Long id) {
        log.debug("Request to get SkillZagUser : {}", id);
        return skillZagUserRepository.findById(id)
            .map(skillZagUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SkillZagUser : {}", id);
        skillZagUserRepository.deleteById(id);
    }
}
