package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.service.SkillZagUserService;
import com.org.skillzag.assesment.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.assesment.service.dto.SkillZagUserDTO;

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
 * REST controller for managing {@link com.org.skillzag.assesment.domain.SkillZagUser}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class SkillZagUserResource {

    private final Logger log = LoggerFactory.getLogger(SkillZagUserResource.class);

    private static final String ENTITY_NAME = "skillzagassesmentSkillZagUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SkillZagUserService skillZagUserService;

    public SkillZagUserResource(SkillZagUserService skillZagUserService) {
        this.skillZagUserService = skillZagUserService;
    }

    /**
     * {@code POST  /skill-zag-users} : Create a new skillZagUser.
     *
     * @param skillZagUserDTO the skillZagUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new skillZagUserDTO, or with status {@code 400 (Bad Request)} if the skillZagUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/skill-zag-users")
    public ResponseEntity<SkillZagUserDTO> createSkillZagUser(@RequestBody SkillZagUserDTO skillZagUserDTO) throws URISyntaxException {
        log.debug("REST request to save SkillZagUser : {}", skillZagUserDTO);
        if (skillZagUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new skillZagUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SkillZagUserDTO result = skillZagUserService.save(skillZagUserDTO);
        return ResponseEntity.created(new URI("/api/skill-zag-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /skill-zag-users} : Updates an existing skillZagUser.
     *
     * @param skillZagUserDTO the skillZagUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated skillZagUserDTO,
     * or with status {@code 400 (Bad Request)} if the skillZagUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the skillZagUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/skill-zag-users")
    public ResponseEntity<SkillZagUserDTO> updateSkillZagUser(@RequestBody SkillZagUserDTO skillZagUserDTO) throws URISyntaxException {
        log.debug("REST request to update SkillZagUser : {}", skillZagUserDTO);
        if (skillZagUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SkillZagUserDTO result = skillZagUserService.save(skillZagUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, skillZagUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /skill-zag-users} : get all the skillZagUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skillZagUsers in body.
     */
    @GetMapping("/skill-zag-users")
    public ResponseEntity<List<SkillZagUserDTO>> getAllSkillZagUsers(Pageable pageable) {
        log.debug("REST request to get a page of SkillZagUsers");
        Page<SkillZagUserDTO> page = skillZagUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /skill-zag-users/:id} : get the "id" skillZagUser.
     *
     * @param id the id of the skillZagUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the skillZagUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/skill-zag-users/{id}")
    public ResponseEntity<SkillZagUserDTO> getSkillZagUser(@PathVariable Long id) {
        log.debug("REST request to get SkillZagUser : {}", id);
        Optional<SkillZagUserDTO> skillZagUserDTO = skillZagUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(skillZagUserDTO);
    }

    /**
     * {@code DELETE  /skill-zag-users/:id} : delete the "id" skillZagUser.
     *
     * @param id the id of the skillZagUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/skill-zag-users/{id}")
    public ResponseEntity<Void> deleteSkillZagUser(@PathVariable Long id) {
        log.debug("REST request to delete SkillZagUser : {}", id);
        skillZagUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
