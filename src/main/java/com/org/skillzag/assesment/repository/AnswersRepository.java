package com.org.skillzag.assesment.repository;

import com.org.skillzag.assesment.domain.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Answers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
}
