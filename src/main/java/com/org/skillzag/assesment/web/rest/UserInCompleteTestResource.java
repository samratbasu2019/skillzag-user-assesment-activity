package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.service.UserInCompleteTestService;
import com.org.skillzag.assesment.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.assesment.service.dto.UserInCompleteTestDTO;

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
 * REST controller for managing {@link com.org.skillzag.assesment.domain.UserInCompleteTest}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserInCompleteTestResource {

    private final Logger log = LoggerFactory.getLogger(UserInCompleteTestResource.class);

    private static final String ENTITY_NAME = "skillzagassesmentUserInCompleteTest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserInCompleteTestService userInCompleteTestService;

    public UserInCompleteTestResource(UserInCompleteTestService userInCompleteTestService) {
        this.userInCompleteTestService = userInCompleteTestService;
    }

    /**
     * {@code POST  /user-in-complete-tests} : Create a new userInCompleteTest.
     *
     * @param userInCompleteTestDTO the userInCompleteTestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userInCompleteTestDTO, or with status {@code 400 (Bad Request)} if the userInCompleteTest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-in-complete-tests")
    public ResponseEntity<UserInCompleteTestDTO> createUserInCompleteTest(@RequestBody UserInCompleteTestDTO userInCompleteTestDTO) throws URISyntaxException {
        log.debug("REST request to save UserInCompleteTest : {}", userInCompleteTestDTO);
        if (userInCompleteTestDTO.getId() != null) {
            throw new BadRequestAlertException("A new userInCompleteTest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserInCompleteTestDTO result = userInCompleteTestService.save(userInCompleteTestDTO);
        return ResponseEntity.created(new URI("/api/user-in-complete-tests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-in-complete-tests} : Updates an existing userInCompleteTest.
     *
     * @param userInCompleteTestDTO the userInCompleteTestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userInCompleteTestDTO,
     * or with status {@code 400 (Bad Request)} if the userInCompleteTestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userInCompleteTestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-in-complete-tests")
    public ResponseEntity<UserInCompleteTestDTO> updateUserInCompleteTest(@RequestBody UserInCompleteTestDTO userInCompleteTestDTO) throws URISyntaxException {
        log.debug("REST request to update UserInCompleteTest : {}", userInCompleteTestDTO);
        if (userInCompleteTestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserInCompleteTestDTO result = userInCompleteTestService.save(userInCompleteTestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userInCompleteTestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-in-complete-tests} : get all the userInCompleteTests.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userInCompleteTests in body.
     */
    @GetMapping("/user-in-complete-tests")
    public ResponseEntity<List<UserInCompleteTestDTO>> getAllUserInCompleteTests(Pageable pageable) {
        log.debug("REST request to get a page of UserInCompleteTests");
        Page<UserInCompleteTestDTO> page = userInCompleteTestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-in-complete-tests/:id} : get the "id" userInCompleteTest.
     *
     * @param id the id of the userInCompleteTestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userInCompleteTestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-in-complete-tests/{id}")
    public ResponseEntity<UserInCompleteTestDTO> getUserInCompleteTest(@PathVariable Long id) {
        log.debug("REST request to get UserInCompleteTest : {}", id);
        Optional<UserInCompleteTestDTO> userInCompleteTestDTO = userInCompleteTestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userInCompleteTestDTO);
    }

    /**
     * {@code DELETE  /user-in-complete-tests/:id} : delete the "id" userInCompleteTest.
     *
     * @param id the id of the userInCompleteTestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-in-complete-tests/{id}")
    public ResponseEntity<Void> deleteUserInCompleteTest(@PathVariable Long id) {
        log.debug("REST request to delete UserInCompleteTest : {}", id);
        userInCompleteTestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
