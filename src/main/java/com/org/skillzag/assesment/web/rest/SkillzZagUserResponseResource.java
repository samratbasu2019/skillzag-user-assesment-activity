package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.service.SkillzZagUserResponseService;
import com.org.skillzag.assesment.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.assesment.service.dto.SkillzZagUserResponseDTO;

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
 * REST controller for managing {@link com.org.skillzag.assesment.domain.SkillzZagUserResponse}.
 */
@RestController
@RequestMapping("/api")
public class SkillzZagUserResponseResource {

    private final Logger log = LoggerFactory.getLogger(SkillzZagUserResponseResource.class);

    private static final String ENTITY_NAME = "skillzagassesmentSkillzZagUserResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SkillzZagUserResponseService skillzZagUserResponseService;

    public SkillzZagUserResponseResource(SkillzZagUserResponseService skillzZagUserResponseService) {
        this.skillzZagUserResponseService = skillzZagUserResponseService;
    }

    /**
     * {@code POST  /skillz-zag-user-responses} : Create a new skillzZagUserResponse.
     *
     * @param skillzZagUserResponseDTO the skillzZagUserResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new skillzZagUserResponseDTO, or with status {@code 400 (Bad Request)} if the skillzZagUserResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/skillz-zag-user-responses")
    public ResponseEntity<SkillzZagUserResponseDTO> createSkillzZagUserResponse(@RequestBody SkillzZagUserResponseDTO skillzZagUserResponseDTO) throws URISyntaxException {
        log.debug("REST request to save SkillzZagUserResponse : {}", skillzZagUserResponseDTO);
        if (skillzZagUserResponseDTO.getId() != null) {
            throw new BadRequestAlertException("A new skillzZagUserResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SkillzZagUserResponseDTO result = skillzZagUserResponseService.save(skillzZagUserResponseDTO);
        return ResponseEntity.created(new URI("/api/skillz-zag-user-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /skillz-zag-user-responses} : Updates an existing skillzZagUserResponse.
     *
     * @param skillzZagUserResponseDTO the skillzZagUserResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated skillzZagUserResponseDTO,
     * or with status {@code 400 (Bad Request)} if the skillzZagUserResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the skillzZagUserResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/skillz-zag-user-responses")
    public ResponseEntity<SkillzZagUserResponseDTO> updateSkillzZagUserResponse(@RequestBody SkillzZagUserResponseDTO skillzZagUserResponseDTO) throws URISyntaxException {
        log.debug("REST request to update SkillzZagUserResponse : {}", skillzZagUserResponseDTO);
        if (skillzZagUserResponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SkillzZagUserResponseDTO result = skillzZagUserResponseService.save(skillzZagUserResponseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, skillzZagUserResponseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /skillz-zag-user-responses} : get all the skillzZagUserResponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skillzZagUserResponses in body.
     */
    @GetMapping("/skillz-zag-user-responses")
    public ResponseEntity<List<SkillzZagUserResponseDTO>> getAllSkillzZagUserResponses(Pageable pageable) {
        log.debug("REST request to get a page of SkillzZagUserResponses");
        Page<SkillzZagUserResponseDTO> page = skillzZagUserResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /skillz-zag-user-responses/:id} : get the "id" skillzZagUserResponse.
     *
     * @param id the id of the skillzZagUserResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the skillzZagUserResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/skillz-zag-user-responses/{id}")
    public ResponseEntity<SkillzZagUserResponseDTO> getSkillzZagUserResponse(@PathVariable Long id) {
        log.debug("REST request to get SkillzZagUserResponse : {}", id);
        Optional<SkillzZagUserResponseDTO> skillzZagUserResponseDTO = skillzZagUserResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(skillzZagUserResponseDTO);
    }

    /**
     * {@code DELETE  /skillz-zag-user-responses/:id} : delete the "id" skillzZagUserResponse.
     *
     * @param id the id of the skillzZagUserResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/skillz-zag-user-responses/{id}")
    public ResponseEntity<Void> deleteSkillzZagUserResponse(@PathVariable Long id) {
        log.debug("REST request to delete SkillzZagUserResponse : {}", id);
        skillzZagUserResponseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
