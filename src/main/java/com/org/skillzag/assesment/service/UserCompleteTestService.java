package com.org.skillzag.assesment.service;

import com.org.skillzag.assesment.service.dto.UserCompleteTestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.assesment.domain.UserCompleteTest}.
 */
public interface UserCompleteTestService {

    /**
     * Save a userCompleteTest.
     *
     * @param userCompleteTestDTO the entity to save.
     * @return the persisted entity.
     */
    UserCompleteTestDTO save(UserCompleteTestDTO userCompleteTestDTO);

    /**
     * Get all the userCompleteTests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserCompleteTestDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userCompleteTest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserCompleteTestDTO> findOne(Long id);

    /**
     * Delete the "id" userCompleteTest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
