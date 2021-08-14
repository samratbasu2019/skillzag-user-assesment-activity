package com.org.skillzag.assesment.service;

import com.org.skillzag.assesment.service.dto.SkillZagUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.assesment.domain.SkillZagUser}.
 */
public interface SkillZagUserService {

    /**
     * Save a skillZagUser.
     *
     * @param skillZagUserDTO the entity to save.
     * @return the persisted entity.
     */
    SkillZagUserDTO save(SkillZagUserDTO skillZagUserDTO);

    /**
     * Get all the skillZagUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SkillZagUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" skillZagUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SkillZagUserDTO> findOne(Long id);

    /**
     * Delete the "id" skillZagUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
