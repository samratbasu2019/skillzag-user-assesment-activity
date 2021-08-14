package com.org.skillzag.assesment.repository;

import com.org.skillzag.assesment.domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Questions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
}
