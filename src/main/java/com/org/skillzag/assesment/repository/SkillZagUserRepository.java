package com.org.skillzag.assesment.repository;

import com.org.skillzag.assesment.domain.SkillZagUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SkillZagUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillZagUserRepository extends JpaRepository<SkillZagUser, Long> {
}
