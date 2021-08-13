package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.SkillzagassesmentApp;
import com.org.skillzag.assesment.config.TestSecurityConfiguration;
import com.org.skillzag.assesment.domain.QuestionSet;
import com.org.skillzag.assesment.repository.QuestionSetRepository;
import com.org.skillzag.assesment.service.QuestionSetService;
import com.org.skillzag.assesment.service.dto.QuestionSetDTO;
import com.org.skillzag.assesment.service.mapper.QuestionSetMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QuestionSetResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagassesmentApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class QuestionSetResourceIT {

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_QUESTION_SET = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_SET = "BBBBBBBBBB";

    private static final Long DEFAULT_SCORE = 1L;
    private static final Long UPDATED_SCORE = 2L;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_SET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_SET_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_OF_QUESTIONS = 1;
    private static final Integer UPDATED_NUMBER_OF_QUESTIONS = 2;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private QuestionSetRepository questionSetRepository;

    @Autowired
    private QuestionSetMapper questionSetMapper;

    @Autowired
    private QuestionSetService questionSetService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionSetMockMvc;

    private QuestionSet questionSet;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSet createEntity(EntityManager em) {
        QuestionSet questionSet = new QuestionSet()
            .isActive(DEFAULT_IS_ACTIVE)
            .questionSet(DEFAULT_QUESTION_SET)
            .score(DEFAULT_SCORE)
            .type(DEFAULT_TYPE)
            .questionSetType(DEFAULT_QUESTION_SET_TYPE)
            .numberOfQuestions(DEFAULT_NUMBER_OF_QUESTIONS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTime(DEFAULT_CREATED_TIME);
        return questionSet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSet createUpdatedEntity(EntityManager em) {
        QuestionSet questionSet = new QuestionSet()
            .isActive(UPDATED_IS_ACTIVE)
            .questionSet(UPDATED_QUESTION_SET)
            .score(UPDATED_SCORE)
            .type(UPDATED_TYPE)
            .questionSetType(UPDATED_QUESTION_SET_TYPE)
            .numberOfQuestions(UPDATED_NUMBER_OF_QUESTIONS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME);
        return questionSet;
    }

    @BeforeEach
    public void initTest() {
        questionSet = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuestionSet() throws Exception {
        int databaseSizeBeforeCreate = questionSetRepository.findAll().size();
        // Create the QuestionSet
        QuestionSetDTO questionSetDTO = questionSetMapper.toDto(questionSet);
        restQuestionSetMockMvc.perform(post("/api/question-sets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionSetDTO)))
            .andExpect(status().isCreated());

        // Validate the QuestionSet in the database
        List<QuestionSet> questionSetList = questionSetRepository.findAll();
        assertThat(questionSetList).hasSize(databaseSizeBeforeCreate + 1);
        QuestionSet testQuestionSet = questionSetList.get(questionSetList.size() - 1);
        assertThat(testQuestionSet.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testQuestionSet.getQuestionSet()).isEqualTo(DEFAULT_QUESTION_SET);
        assertThat(testQuestionSet.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testQuestionSet.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testQuestionSet.getQuestionSetType()).isEqualTo(DEFAULT_QUESTION_SET_TYPE);
        assertThat(testQuestionSet.getNumberOfQuestions()).isEqualTo(DEFAULT_NUMBER_OF_QUESTIONS);
        assertThat(testQuestionSet.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testQuestionSet.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
    }

    @Test
    @Transactional
    public void createQuestionSetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionSetRepository.findAll().size();

        // Create the QuestionSet with an existing ID
        questionSet.setId(1L);
        QuestionSetDTO questionSetDTO = questionSetMapper.toDto(questionSet);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionSetMockMvc.perform(post("/api/question-sets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionSetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuestionSet in the database
        List<QuestionSet> questionSetList = questionSetRepository.findAll();
        assertThat(questionSetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQuestionSets() throws Exception {
        // Initialize the database
        questionSetRepository.saveAndFlush(questionSet);

        // Get all the questionSetList
        restQuestionSetMockMvc.perform(get("/api/question-sets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionSet.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].questionSet").value(hasItem(DEFAULT_QUESTION_SET)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].questionSetType").value(hasItem(DEFAULT_QUESTION_SET_TYPE)))
            .andExpect(jsonPath("$.[*].numberOfQuestions").value(hasItem(DEFAULT_NUMBER_OF_QUESTIONS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getQuestionSet() throws Exception {
        // Initialize the database
        questionSetRepository.saveAndFlush(questionSet);

        // Get the questionSet
        restQuestionSetMockMvc.perform(get("/api/question-sets/{id}", questionSet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionSet.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.questionSet").value(DEFAULT_QUESTION_SET))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.questionSetType").value(DEFAULT_QUESTION_SET_TYPE))
            .andExpect(jsonPath("$.numberOfQuestions").value(DEFAULT_NUMBER_OF_QUESTIONS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTime").value(DEFAULT_CREATED_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingQuestionSet() throws Exception {
        // Get the questionSet
        restQuestionSetMockMvc.perform(get("/api/question-sets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuestionSet() throws Exception {
        // Initialize the database
        questionSetRepository.saveAndFlush(questionSet);

        int databaseSizeBeforeUpdate = questionSetRepository.findAll().size();

        // Update the questionSet
        QuestionSet updatedQuestionSet = questionSetRepository.findById(questionSet.getId()).get();
        // Disconnect from session so that the updates on updatedQuestionSet are not directly saved in db
        em.detach(updatedQuestionSet);
        updatedQuestionSet
            .isActive(UPDATED_IS_ACTIVE)
            .questionSet(UPDATED_QUESTION_SET)
            .score(UPDATED_SCORE)
            .type(UPDATED_TYPE)
            .questionSetType(UPDATED_QUESTION_SET_TYPE)
            .numberOfQuestions(UPDATED_NUMBER_OF_QUESTIONS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME);
        QuestionSetDTO questionSetDTO = questionSetMapper.toDto(updatedQuestionSet);

        restQuestionSetMockMvc.perform(put("/api/question-sets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionSetDTO)))
            .andExpect(status().isOk());

        // Validate the QuestionSet in the database
        List<QuestionSet> questionSetList = questionSetRepository.findAll();
        assertThat(questionSetList).hasSize(databaseSizeBeforeUpdate);
        QuestionSet testQuestionSet = questionSetList.get(questionSetList.size() - 1);
        assertThat(testQuestionSet.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testQuestionSet.getQuestionSet()).isEqualTo(UPDATED_QUESTION_SET);
        assertThat(testQuestionSet.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testQuestionSet.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testQuestionSet.getQuestionSetType()).isEqualTo(UPDATED_QUESTION_SET_TYPE);
        assertThat(testQuestionSet.getNumberOfQuestions()).isEqualTo(UPDATED_NUMBER_OF_QUESTIONS);
        assertThat(testQuestionSet.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testQuestionSet.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingQuestionSet() throws Exception {
        int databaseSizeBeforeUpdate = questionSetRepository.findAll().size();

        // Create the QuestionSet
        QuestionSetDTO questionSetDTO = questionSetMapper.toDto(questionSet);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionSetMockMvc.perform(put("/api/question-sets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionSetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuestionSet in the database
        List<QuestionSet> questionSetList = questionSetRepository.findAll();
        assertThat(questionSetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuestionSet() throws Exception {
        // Initialize the database
        questionSetRepository.saveAndFlush(questionSet);

        int databaseSizeBeforeDelete = questionSetRepository.findAll().size();

        // Delete the questionSet
        restQuestionSetMockMvc.perform(delete("/api/question-sets/{id}", questionSet.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuestionSet> questionSetList = questionSetRepository.findAll();
        assertThat(questionSetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
