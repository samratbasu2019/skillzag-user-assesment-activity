package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.SkillzagassesmentApp;
import com.org.skillzag.assesment.config.TestSecurityConfiguration;
import com.org.skillzag.assesment.domain.Questions;
import com.org.skillzag.assesment.repository.QuestionsRepository;
import com.org.skillzag.assesment.service.QuestionsService;
import com.org.skillzag.assesment.service.dto.QuestionsDTO;
import com.org.skillzag.assesment.service.mapper.QuestionsMapper;

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
 * Integration tests for the {@link QuestionsResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagassesmentApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class QuestionsResourceIT {

    private static final Boolean DEFAULT_IS_TRUE_FALSE = false;
    private static final Boolean UPDATED_IS_TRUE_FALSE = true;

    private static final Boolean DEFAULT_IS_MULTIPLE = false;
    private static final Boolean UPDATED_IS_MULTIPLE = true;

    private static final Boolean DEFAULT_IS_ORDER_BY = false;
    private static final Boolean UPDATED_IS_ORDER_BY = true;

    private static final Boolean DEFAULT_IS_DISCUSSION = false;
    private static final Boolean UPDATED_IS_DISCUSSION = true;

    private static final String DEFAULT_VIDEO_URL = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionsMockMvc;

    private Questions questions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Questions createEntity(EntityManager em) {
        Questions questions = new Questions()
            .isTrueFalse(DEFAULT_IS_TRUE_FALSE)
            .isMultiple(DEFAULT_IS_MULTIPLE)
            .isOrderBy(DEFAULT_IS_ORDER_BY)
            .isDiscussion(DEFAULT_IS_DISCUSSION)
            .videoUrl(DEFAULT_VIDEO_URL)
            .imageUrl(DEFAULT_IMAGE_URL)
            .isActive(DEFAULT_IS_ACTIVE)
            .question(DEFAULT_QUESTION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTime(DEFAULT_CREATED_TIME)
            .score(DEFAULT_SCORE);
        return questions;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Questions createUpdatedEntity(EntityManager em) {
        Questions questions = new Questions()
            .isTrueFalse(UPDATED_IS_TRUE_FALSE)
            .isMultiple(UPDATED_IS_MULTIPLE)
            .isOrderBy(UPDATED_IS_ORDER_BY)
            .isDiscussion(UPDATED_IS_DISCUSSION)
            .videoUrl(UPDATED_VIDEO_URL)
            .imageUrl(UPDATED_IMAGE_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .question(UPDATED_QUESTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME)
            .score(UPDATED_SCORE);
        return questions;
    }

    @BeforeEach
    public void initTest() {
        questions = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuestions() throws Exception {
        int databaseSizeBeforeCreate = questionsRepository.findAll().size();
        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);
        restQuestionsMockMvc.perform(post("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionsDTO)))
            .andExpect(status().isCreated());

        // Validate the Questions in the database
        List<Questions> questionsList = questionsRepository.findAll();
        assertThat(questionsList).hasSize(databaseSizeBeforeCreate + 1);
        Questions testQuestions = questionsList.get(questionsList.size() - 1);
        assertThat(testQuestions.isIsTrueFalse()).isEqualTo(DEFAULT_IS_TRUE_FALSE);
        assertThat(testQuestions.isIsMultiple()).isEqualTo(DEFAULT_IS_MULTIPLE);
        assertThat(testQuestions.isIsOrderBy()).isEqualTo(DEFAULT_IS_ORDER_BY);
        assertThat(testQuestions.isIsDiscussion()).isEqualTo(DEFAULT_IS_DISCUSSION);
        assertThat(testQuestions.getVideoUrl()).isEqualTo(DEFAULT_VIDEO_URL);
        assertThat(testQuestions.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testQuestions.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testQuestions.getQuestion()).isEqualTo(DEFAULT_QUESTION);
        assertThat(testQuestions.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testQuestions.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
        assertThat(testQuestions.getScore()).isEqualTo(DEFAULT_SCORE);
    }

    @Test
    @Transactional
    public void createQuestionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionsRepository.findAll().size();

        // Create the Questions with an existing ID
        questions.setId(1L);
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionsMockMvc.perform(post("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Questions in the database
        List<Questions> questionsList = questionsRepository.findAll();
        assertThat(questionsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQuestions() throws Exception {
        // Initialize the database
        questionsRepository.saveAndFlush(questions);

        // Get all the questionsList
        restQuestionsMockMvc.perform(get("/api/questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questions.getId().intValue())))
            .andExpect(jsonPath("$.[*].isTrueFalse").value(hasItem(DEFAULT_IS_TRUE_FALSE.booleanValue())))
            .andExpect(jsonPath("$.[*].isMultiple").value(hasItem(DEFAULT_IS_MULTIPLE.booleanValue())))
            .andExpect(jsonPath("$.[*].isOrderBy").value(hasItem(DEFAULT_IS_ORDER_BY.booleanValue())))
            .andExpect(jsonPath("$.[*].isDiscussion").value(hasItem(DEFAULT_IS_DISCUSSION.booleanValue())))
            .andExpect(jsonPath("$.[*].videoUrl").value(hasItem(DEFAULT_VIDEO_URL)))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)));
    }
    
    @Test
    @Transactional
    public void getQuestions() throws Exception {
        // Initialize the database
        questionsRepository.saveAndFlush(questions);

        // Get the questions
        restQuestionsMockMvc.perform(get("/api/questions/{id}", questions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questions.getId().intValue()))
            .andExpect(jsonPath("$.isTrueFalse").value(DEFAULT_IS_TRUE_FALSE.booleanValue()))
            .andExpect(jsonPath("$.isMultiple").value(DEFAULT_IS_MULTIPLE.booleanValue()))
            .andExpect(jsonPath("$.isOrderBy").value(DEFAULT_IS_ORDER_BY.booleanValue()))
            .andExpect(jsonPath("$.isDiscussion").value(DEFAULT_IS_DISCUSSION.booleanValue()))
            .andExpect(jsonPath("$.videoUrl").value(DEFAULT_VIDEO_URL))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.question").value(DEFAULT_QUESTION))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTime").value(DEFAULT_CREATED_TIME.toString()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE));
    }
    @Test
    @Transactional
    public void getNonExistingQuestions() throws Exception {
        // Get the questions
        restQuestionsMockMvc.perform(get("/api/questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuestions() throws Exception {
        // Initialize the database
        questionsRepository.saveAndFlush(questions);

        int databaseSizeBeforeUpdate = questionsRepository.findAll().size();

        // Update the questions
        Questions updatedQuestions = questionsRepository.findById(questions.getId()).get();
        // Disconnect from session so that the updates on updatedQuestions are not directly saved in db
        em.detach(updatedQuestions);
        updatedQuestions
            .isTrueFalse(UPDATED_IS_TRUE_FALSE)
            .isMultiple(UPDATED_IS_MULTIPLE)
            .isOrderBy(UPDATED_IS_ORDER_BY)
            .isDiscussion(UPDATED_IS_DISCUSSION)
            .videoUrl(UPDATED_VIDEO_URL)
            .imageUrl(UPDATED_IMAGE_URL)
            .isActive(UPDATED_IS_ACTIVE)
            .question(UPDATED_QUESTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME)
            .score(UPDATED_SCORE);
        QuestionsDTO questionsDTO = questionsMapper.toDto(updatedQuestions);

        restQuestionsMockMvc.perform(put("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionsDTO)))
            .andExpect(status().isOk());

        // Validate the Questions in the database
        List<Questions> questionsList = questionsRepository.findAll();
        assertThat(questionsList).hasSize(databaseSizeBeforeUpdate);
        Questions testQuestions = questionsList.get(questionsList.size() - 1);
        assertThat(testQuestions.isIsTrueFalse()).isEqualTo(UPDATED_IS_TRUE_FALSE);
        assertThat(testQuestions.isIsMultiple()).isEqualTo(UPDATED_IS_MULTIPLE);
        assertThat(testQuestions.isIsOrderBy()).isEqualTo(UPDATED_IS_ORDER_BY);
        assertThat(testQuestions.isIsDiscussion()).isEqualTo(UPDATED_IS_DISCUSSION);
        assertThat(testQuestions.getVideoUrl()).isEqualTo(UPDATED_VIDEO_URL);
        assertThat(testQuestions.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testQuestions.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testQuestions.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testQuestions.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testQuestions.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testQuestions.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuestions() throws Exception {
        int databaseSizeBeforeUpdate = questionsRepository.findAll().size();

        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionsMockMvc.perform(put("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Questions in the database
        List<Questions> questionsList = questionsRepository.findAll();
        assertThat(questionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuestions() throws Exception {
        // Initialize the database
        questionsRepository.saveAndFlush(questions);

        int databaseSizeBeforeDelete = questionsRepository.findAll().size();

        // Delete the questions
        restQuestionsMockMvc.perform(delete("/api/questions/{id}", questions.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Questions> questionsList = questionsRepository.findAll();
        assertThat(questionsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
