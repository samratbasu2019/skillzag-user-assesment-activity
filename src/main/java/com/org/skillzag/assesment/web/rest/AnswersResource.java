package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.service.AnswersService;
import com.org.skillzag.assesment.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.assesment.service.dto.AnswersDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.org.skillzag.assesment.domain.Answers}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AnswersResource {

    private final Logger log = LoggerFactory.getLogger(AnswersResource.class);

    private static final String ENTITY_NAME = "skillzagassesmentAnswers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnswersService answersService;

    public AnswersResource(AnswersService answersService) {
        this.answersService = answersService;
    }

    /**
     * {@code POST  /answers} : Create a new answers.
     *
     * @param answersDTO the answersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new answersDTO, or with status {@code 400 (Bad Request)} if the answers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/answers")
    public ResponseEntity<AnswersDTO> createAnswers(@RequestBody AnswersDTO answersDTO) throws URISyntaxException {
        log.debug("REST request to save Answers : {}", answersDTO);
        if (answersDTO.getId() != null) {
            throw new BadRequestAlertException("A new answers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnswersDTO result = answersService.save(answersDTO);
        return ResponseEntity.created(new URI("/api/answers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /answers} : Updates an existing answers.
     *
     * @param answersDTO the answersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answersDTO,
     * or with status {@code 400 (Bad Request)} if the answersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the answersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/answers")
    public ResponseEntity<AnswersDTO> updateAnswers(@RequestBody AnswersDTO answersDTO) throws URISyntaxException {
        log.debug("REST request to update Answers : {}", answersDTO);
        if (answersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnswersDTO result = answersService.save(answersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, answersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /answers} : get all the answers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answers in body.
     */
    @GetMapping("/answers")
    public ResponseEntity<List<AnswersDTO>> getAllAnswers(Pageable pageable) {
        log.debug("REST request to get a page of Answers");
        Page<AnswersDTO> page = answersService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /answers/:id} : get the "id" answers.
     *
     * @param id the id of the answersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answers/{id}")
    public ResponseEntity<AnswersDTO> getAnswers(@PathVariable Long id) {
        log.debug("REST request to get Answers : {}", id);
        Optional<AnswersDTO> answersDTO = answersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answersDTO);
    }

    /**
     * {@code DELETE  /answers/:id} : delete the "id" answers.
     *
     * @param id the id of the answersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/answers/{id}")
    public ResponseEntity<Void> deleteAnswers(@PathVariable Long id) {
        log.debug("REST request to delete Answers : {}", id);
        answersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
