package com.org.skillzag.assesment.service;

import com.org.skillzag.assesment.service.dto.SkillzZagUserResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.assesment.domain.SkillzZagUserResponse}.
 */
public interface SkillzZagUserResponseService {

    /**
     * Save a skillzZagUserResponse.
     *
     * @param skillzZagUserResponseDTO the entity to save.
     * @return the persisted entity.
     */
    SkillzZagUserResponseDTO save(SkillzZagUserResponseDTO skillzZagUserResponseDTO);

    /**
     * Get all the skillzZagUserResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SkillzZagUserResponseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" skillzZagUserResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SkillzZagUserResponseDTO> findOne(Long id);

    /**
     * Delete the "id" skillzZagUserResponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
