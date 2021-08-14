package com.org.skillzag.assesment.repository;

import com.org.skillzag.assesment.domain.QuestionSet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Spring Data  repository for the Questions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CRUDQuestionAnswerRepository extends CrudRepository<QuestionSet, Long> {
    @Query(value = "select qs.id, a.is_active, a.is_correct, a.answer, q.question from \n" +
        "question_set qs, questions q, answers a where qs.id = q.question_set_id and a.questions_id = q.id and qs.id = :id",
        nativeQuery = true)
    List<Map<String, Object>> findQuestionAnswers(@Param("id") Long id);
}
