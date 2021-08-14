package com.org.skillzag.assesment.service.mapper;

import java.util.List;
import java.util.Map;

/**
 * Service Interface for managing {@link com.org.skillzag.assesment.domain.Questions}.
 */
public interface QuestionAnswersService {

    /**
     * Get the "id" questions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    List<Map<String, Object>> findQuestionAnswerBySetId(Long id);

    /**
     * Get the "id" questions.
     *
     * @param type the id of the entity.
     * @return the entity.
     */
    List<Map<String, Object>>  findQuestionAnswerByType(String type);
}
