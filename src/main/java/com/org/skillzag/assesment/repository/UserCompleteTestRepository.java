package com.org.skillzag.assesment.repository;

import com.org.skillzag.assesment.domain.UserCompleteTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserCompleteTest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCompleteTestRepository extends JpaRepository<UserCompleteTest, Long> {
}
