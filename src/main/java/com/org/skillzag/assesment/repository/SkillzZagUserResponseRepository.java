package com.org.skillzag.assesment.repository;

import com.org.skillzag.assesment.domain.SkillzZagUserResponse;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SkillzZagUserResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillzZagUserResponseRepository extends JpaRepository<SkillzZagUserResponse, Long> {
}
