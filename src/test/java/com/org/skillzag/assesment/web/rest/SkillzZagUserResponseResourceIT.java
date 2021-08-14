package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.SkillzagassesmentApp;
import com.org.skillzag.assesment.config.TestSecurityConfiguration;
import com.org.skillzag.assesment.domain.SkillzZagUserResponse;
import com.org.skillzag.assesment.repository.SkillzZagUserResponseRepository;
import com.org.skillzag.assesment.service.SkillzZagUserResponseService;
import com.org.skillzag.assesment.service.dto.SkillzZagUserResponseDTO;
import com.org.skillzag.assesment.service.mapper.SkillzZagUserResponseMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SkillzZagUserResponseResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagassesmentApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class SkillzZagUserResponseResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_QUESTION_ID = 1L;
    private static final Long UPDATED_QUESTION_ID = 2L;

    private static final Long DEFAULT_ANSWER_ID = 1L;
    private static final Long UPDATED_ANSWER_ID = 2L;

    private static final Long DEFAULT_QUESTION_SET_ID = 1L;
    private static final Long UPDATED_QUESTION_SET_ID = 2L;

    @Autowired
    private SkillzZagUserResponseRepository skillzZagUserResponseRepository;

    @Autowired
    private SkillzZagUserResponseMapper skillzZagUserResponseMapper;

    @Autowired
    private SkillzZagUserResponseService skillzZagUserResponseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSkillzZagUserResponseMockMvc;

    private SkillzZagUserResponse skillzZagUserResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillzZagUserResponse createEntity(EntityManager em) {
        SkillzZagUserResponse skillzZagUserResponse = new SkillzZagUserResponse()
            .userId(DEFAULT_USER_ID)
            .questionId(DEFAULT_QUESTION_ID)
            .answerId(DEFAULT_ANSWER_ID)
            .questionSetId(DEFAULT_QUESTION_SET_ID);
        return skillzZagUserResponse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillzZagUserResponse createUpdatedEntity(EntityManager em) {
        SkillzZagUserResponse skillzZagUserResponse = new SkillzZagUserResponse()
            .userId(UPDATED_USER_ID)
            .questionId(UPDATED_QUESTION_ID)
            .answerId(UPDATED_ANSWER_ID)
            .questionSetId(UPDATED_QUESTION_SET_ID);
        return skillzZagUserResponse;
    }

    @BeforeEach
    public void initTest() {
        skillzZagUserResponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkillzZagUserResponse() throws Exception {
        int databaseSizeBeforeCreate = skillzZagUserResponseRepository.findAll().size();
        // Create the SkillzZagUserResponse
        SkillzZagUserResponseDTO skillzZagUserResponseDTO = skillzZagUserResponseMapper.toDto(skillzZagUserResponse);
        restSkillzZagUserResponseMockMvc.perform(post("/api/skillz-zag-user-responses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillzZagUserResponseDTO)))
            .andExpect(status().isCreated());

        // Validate the SkillzZagUserResponse in the database
        List<SkillzZagUserResponse> skillzZagUserResponseList = skillzZagUserResponseRepository.findAll();
        assertThat(skillzZagUserResponseList).hasSize(databaseSizeBeforeCreate + 1);
        SkillzZagUserResponse testSkillzZagUserResponse = skillzZagUserResponseList.get(skillzZagUserResponseList.size() - 1);
        assertThat(testSkillzZagUserResponse.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSkillzZagUserResponse.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
        assertThat(testSkillzZagUserResponse.getAnswerId()).isEqualTo(DEFAULT_ANSWER_ID);
        assertThat(testSkillzZagUserResponse.getQuestionSetId()).isEqualTo(DEFAULT_QUESTION_SET_ID);
    }

    @Test
    @Transactional
    public void createSkillzZagUserResponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skillzZagUserResponseRepository.findAll().size();

        // Create the SkillzZagUserResponse with an existing ID
        skillzZagUserResponse.setId(1L);
        SkillzZagUserResponseDTO skillzZagUserResponseDTO = skillzZagUserResponseMapper.toDto(skillzZagUserResponse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillzZagUserResponseMockMvc.perform(post("/api/skillz-zag-user-responses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillzZagUserResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SkillzZagUserResponse in the database
        List<SkillzZagUserResponse> skillzZagUserResponseList = skillzZagUserResponseRepository.findAll();
        assertThat(skillzZagUserResponseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSkillzZagUserResponses() throws Exception {
        // Initialize the database
        skillzZagUserResponseRepository.saveAndFlush(skillzZagUserResponse);

        // Get all the skillzZagUserResponseList
        restSkillzZagUserResponseMockMvc.perform(get("/api/skillz-zag-user-responses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skillzZagUserResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].answerId").value(hasItem(DEFAULT_ANSWER_ID.intValue())))
            .andExpect(jsonPath("$.[*].questionSetId").value(hasItem(DEFAULT_QUESTION_SET_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getSkillzZagUserResponse() throws Exception {
        // Initialize the database
        skillzZagUserResponseRepository.saveAndFlush(skillzZagUserResponse);

        // Get the skillzZagUserResponse
        restSkillzZagUserResponseMockMvc.perform(get("/api/skillz-zag-user-responses/{id}", skillzZagUserResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(skillzZagUserResponse.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID.intValue()))
            .andExpect(jsonPath("$.answerId").value(DEFAULT_ANSWER_ID.intValue()))
            .andExpect(jsonPath("$.questionSetId").value(DEFAULT_QUESTION_SET_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSkillzZagUserResponse() throws Exception {
        // Get the skillzZagUserResponse
        restSkillzZagUserResponseMockMvc.perform(get("/api/skillz-zag-user-responses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkillzZagUserResponse() throws Exception {
        // Initialize the database
        skillzZagUserResponseRepository.saveAndFlush(skillzZagUserResponse);

        int databaseSizeBeforeUpdate = skillzZagUserResponseRepository.findAll().size();

        // Update the skillzZagUserResponse
        SkillzZagUserResponse updatedSkillzZagUserResponse = skillzZagUserResponseRepository.findById(skillzZagUserResponse.getId()).get();
        // Disconnect from session so that the updates on updatedSkillzZagUserResponse are not directly saved in db
        em.detach(updatedSkillzZagUserResponse);
        updatedSkillzZagUserResponse
            .userId(UPDATED_USER_ID)
            .questionId(UPDATED_QUESTION_ID)
            .answerId(UPDATED_ANSWER_ID)
            .questionSetId(UPDATED_QUESTION_SET_ID);
        SkillzZagUserResponseDTO skillzZagUserResponseDTO = skillzZagUserResponseMapper.toDto(updatedSkillzZagUserResponse);

        restSkillzZagUserResponseMockMvc.perform(put("/api/skillz-zag-user-responses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillzZagUserResponseDTO)))
            .andExpect(status().isOk());

        // Validate the SkillzZagUserResponse in the database
        List<SkillzZagUserResponse> skillzZagUserResponseList = skillzZagUserResponseRepository.findAll();
        assertThat(skillzZagUserResponseList).hasSize(databaseSizeBeforeUpdate);
        SkillzZagUserResponse testSkillzZagUserResponse = skillzZagUserResponseList.get(skillzZagUserResponseList.size() - 1);
        assertThat(testSkillzZagUserResponse.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSkillzZagUserResponse.getQuestionId()).isEqualTo(UPDATED_QUESTION_ID);
        assertThat(testSkillzZagUserResponse.getAnswerId()).isEqualTo(UPDATED_ANSWER_ID);
        assertThat(testSkillzZagUserResponse.getQuestionSetId()).isEqualTo(UPDATED_QUESTION_SET_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSkillzZagUserResponse() throws Exception {
        int databaseSizeBeforeUpdate = skillzZagUserResponseRepository.findAll().size();

        // Create the SkillzZagUserResponse
        SkillzZagUserResponseDTO skillzZagUserResponseDTO = skillzZagUserResponseMapper.toDto(skillzZagUserResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkillzZagUserResponseMockMvc.perform(put("/api/skillz-zag-user-responses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillzZagUserResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SkillzZagUserResponse in the database
        List<SkillzZagUserResponse> skillzZagUserResponseList = skillzZagUserResponseRepository.findAll();
        assertThat(skillzZagUserResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSkillzZagUserResponse() throws Exception {
        // Initialize the database
        skillzZagUserResponseRepository.saveAndFlush(skillzZagUserResponse);

        int databaseSizeBeforeDelete = skillzZagUserResponseRepository.findAll().size();

        // Delete the skillzZagUserResponse
        restSkillzZagUserResponseMockMvc.perform(delete("/api/skillz-zag-user-responses/{id}", skillzZagUserResponse.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SkillzZagUserResponse> skillzZagUserResponseList = skillzZagUserResponseRepository.findAll();
        assertThat(skillzZagUserResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
