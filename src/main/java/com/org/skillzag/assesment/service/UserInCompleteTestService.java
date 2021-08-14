package com.org.skillzag.assesment.service;

import com.org.skillzag.assesment.service.dto.UserInCompleteTestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.assesment.domain.UserInCompleteTest}.
 */
public interface UserInCompleteTestService {

    /**
     * Save a userInCompleteTest.
     *
     * @param userInCompleteTestDTO the entity to save.
     * @return the persisted entity.
     */
    UserInCompleteTestDTO save(UserInCompleteTestDTO userInCompleteTestDTO);

    /**
     * Get all the userInCompleteTests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserInCompleteTestDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userInCompleteTest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserInCompleteTestDTO> findOne(Long id);

    /**
     * Delete the "id" userInCompleteTest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
