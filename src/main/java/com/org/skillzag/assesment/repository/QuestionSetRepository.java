package com.org.skillzag.assesment.repository;

import com.org.skillzag.assesment.domain.QuestionSet;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QuestionSet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionSetRepository extends JpaRepository<QuestionSet, Long> {
}
