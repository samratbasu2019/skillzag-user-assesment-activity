package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.service.QuestionsService;
import com.org.skillzag.assesment.service.dto.QuestionsDTO;
import com.org.skillzag.assesment.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.org.skillzag.assesment.domain.Questions}.
 */
@RestController
@RequestMapping("/api")
public class QuestionsResource {

    private final Logger log = LoggerFactory.getLogger(QuestionsResource.class);

    private static final String ENTITY_NAME = "skillzagassesmentQuestions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionsService questionsService;

    public QuestionsResource(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    /**
     * {@code POST  /questions} : Create a new questions.
     *
     * @param questionsDTO the questionsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionsDTO, or with status {@code 400 (Bad Request)} if the questions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/questions")
    public ResponseEntity<QuestionsDTO> createQuestions(@RequestBody QuestionsDTO questionsDTO) throws URISyntaxException {
        log.debug("REST request to save Questions : {}", questionsDTO);
        if (questionsDTO.getId() != null) {
            throw new BadRequestAlertException("A new questions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionsDTO result = questionsService.save(questionsDTO);
        return ResponseEntity.created(new URI("/api/questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /questions} : Updates an existing questions.
     *
     * @param questionsDTO the questionsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionsDTO,
     * or with status {@code 400 (Bad Request)} if the questionsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/questions")
    public ResponseEntity<QuestionsDTO> updateQuestions(@RequestBody QuestionsDTO questionsDTO) throws URISyntaxException {
        log.debug("REST request to update Questions : {}", questionsDTO);
        if (questionsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuestionsDTO result = questionsService.save(questionsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, questionsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /questions} : get all the questions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questions in body.
     */
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionsDTO>> getAllQuestions(Pageable pageable) {
        log.debug("REST request to get a page of Questions");
        Page<QuestionsDTO> page = questionsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /questions/:id} : get the "id" questions.
     *
     * @param id the id of the questionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionsDTO> getQuestions(@PathVariable Long id) {
        log.debug("REST request to get Questions : {}", id);
        Optional<QuestionsDTO> questionsDTO = questionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionsDTO);
    }

    /**
     * {@code DELETE  /questions/:id} : delete the "id" questions.
     *
     * @param id the id of the questionsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Void> deleteQuestions(@PathVariable Long id) {
        log.debug("REST request to delete Questions : {}", id);
        questionsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
