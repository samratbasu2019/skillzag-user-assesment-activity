package com.org.skillzag.assesment.service;

import com.org.skillzag.assesment.service.dto.QuestionSetDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.assesment.domain.QuestionSet}.
 */
public interface QuestionSetService {

    /**
     * Save a questionSet.
     *
     * @param questionSetDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionSetDTO save(QuestionSetDTO questionSetDTO);

    /**
     * Get all the questionSets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuestionSetDTO> findAll(Pageable pageable);


    /**
     * Get the "id" questionSet.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionSetDTO> findOne(Long id);

    /**
     * Delete the "id" questionSet.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
