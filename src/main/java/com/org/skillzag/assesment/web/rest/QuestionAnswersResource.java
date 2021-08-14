package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.service.mapper.QuestionAnswersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing {@link com.org.skillzag.assesment.domain.Questions}.
 */
@RestController
@RequestMapping("/api")
public class QuestionAnswersResource {

    private final Logger log = LoggerFactory.getLogger(QuestionAnswersResource.class);

    private final QuestionAnswersService questionAnswersService;

    public QuestionAnswersResource(QuestionAnswersService questionAnswersService) {
        this.questionAnswersService = questionAnswersService;
    }

    /**
     * {@code GET  /questions/:id} : get the "id" questions.
     *
     * @param id the id of the questionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-answer/{id}")
    public ResponseEntity<?> getQuestionAnswer(@PathVariable Long id) {
        log.debug("REST request to get Question Answers : {}", id);
        List<Map<String, Object>>  questionsDTO = questionAnswersService.findQuestionAnswer(id);
        return ResponseEntity.ok().body(questionsDTO);
    }
}
