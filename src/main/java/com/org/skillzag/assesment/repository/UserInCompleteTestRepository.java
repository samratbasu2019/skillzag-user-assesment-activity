package com.org.skillzag.assesment.repository;

import com.org.skillzag.assesment.domain.UserInCompleteTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserInCompleteTest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserInCompleteTestRepository extends JpaRepository<UserInCompleteTest, Long> {
}
