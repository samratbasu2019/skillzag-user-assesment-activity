package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.service.mapper.QuestionAnswersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing {@link com.org.skillzag.assesment.domain.Questions}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionAnswersResource {

    private final Logger log = LoggerFactory.getLogger(QuestionAnswersResource.class);

    private final QuestionAnswersService questionAnswersService;

    public QuestionAnswersResource(QuestionAnswersService questionAnswersService) {
        this.questionAnswersService = questionAnswersService;
    }

    /**
     * {@code GET  /questions/:questionSetId} : get the "id" questions.
     *
     * @param questionSetId the id of the questionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-answer-byid/{questionSetId}")
    public ResponseEntity<?> getQuestionAnswer(@PathVariable Long questionSetId) {
        log.debug("REST request to get Question Answers : {}", questionSetId);
        List<Map<String, Object>>  questionsDTO = questionAnswersService.findQuestionAnswerBySetId(questionSetId);
        return ResponseEntity.ok().body(questionsDTO);
    }

    /**
     * {@code GET  /questions/:type} : get the "id" questions.
     *
     * @param type the id of the questionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-answer-bytype/{type}")
    public ResponseEntity<?> getQuestionAnswerType(@PathVariable String type) {
        log.debug("REST request to get Question Answers : {}", type);
        List<Map<String, Object>>  questionsDTO = questionAnswersService.findQuestionAnswerByType(type);
        return ResponseEntity.ok().body(questionsDTO);
    }
}
