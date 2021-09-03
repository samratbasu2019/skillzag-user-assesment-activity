package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.service.UserCompleteTestService;
import com.org.skillzag.assesment.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.assesment.service.dto.UserCompleteTestDTO;

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
 * REST controller for managing {@link com.org.skillzag.assesment.domain.UserCompleteTest}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserCompleteTestResource {

    private final Logger log = LoggerFactory.getLogger(UserCompleteTestResource.class);

    private static final String ENTITY_NAME = "skillzagassesmentUserCompleteTest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserCompleteTestService userCompleteTestService;

    public UserCompleteTestResource(UserCompleteTestService userCompleteTestService) {
        this.userCompleteTestService = userCompleteTestService;
    }

    /**
     * {@code POST  /user-complete-tests} : Create a new userCompleteTest.
     *
     * @param userCompleteTestDTO the userCompleteTestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userCompleteTestDTO, or with status {@code 400 (Bad Request)} if the userCompleteTest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-complete-tests")
    public ResponseEntity<UserCompleteTestDTO> createUserCompleteTest(@RequestBody UserCompleteTestDTO userCompleteTestDTO) throws URISyntaxException {
        log.debug("REST request to save UserCompleteTest : {}", userCompleteTestDTO);
        if (userCompleteTestDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCompleteTest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserCompleteTestDTO result = userCompleteTestService.save(userCompleteTestDTO);
        return ResponseEntity.created(new URI("/api/user-complete-tests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-complete-tests} : Updates an existing userCompleteTest.
     *
     * @param userCompleteTestDTO the userCompleteTestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userCompleteTestDTO,
     * or with status {@code 400 (Bad Request)} if the userCompleteTestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userCompleteTestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-complete-tests")
    public ResponseEntity<UserCompleteTestDTO> updateUserCompleteTest(@RequestBody UserCompleteTestDTO userCompleteTestDTO) throws URISyntaxException {
        log.debug("REST request to update UserCompleteTest : {}", userCompleteTestDTO);
        if (userCompleteTestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserCompleteTestDTO result = userCompleteTestService.save(userCompleteTestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userCompleteTestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-complete-tests} : get all the userCompleteTests.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userCompleteTests in body.
     */
    @GetMapping("/user-complete-tests")
    public ResponseEntity<List<UserCompleteTestDTO>> getAllUserCompleteTests(Pageable pageable) {
        log.debug("REST request to get a page of UserCompleteTests");
        Page<UserCompleteTestDTO> page = userCompleteTestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-complete-tests/:id} : get the "id" userCompleteTest.
     *
     * @param id the id of the userCompleteTestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userCompleteTestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-complete-tests/{id}")
    public ResponseEntity<UserCompleteTestDTO> getUserCompleteTest(@PathVariable Long id) {
        log.debug("REST request to get UserCompleteTest : {}", id);
        Optional<UserCompleteTestDTO> userCompleteTestDTO = userCompleteTestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userCompleteTestDTO);
    }

    /**
     * {@code DELETE  /user-complete-tests/:id} : delete the "id" userCompleteTest.
     *
     * @param id the id of the userCompleteTestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-complete-tests/{id}")
    public ResponseEntity<Void> deleteUserCompleteTest(@PathVariable Long id) {
        log.debug("REST request to delete UserCompleteTest : {}", id);
        userCompleteTestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
