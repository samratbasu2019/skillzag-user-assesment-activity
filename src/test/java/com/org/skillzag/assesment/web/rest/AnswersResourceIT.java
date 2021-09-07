package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.SkillzagassesmentApp;
import com.org.skillzag.assesment.config.TestSecurityConfiguration;
import com.org.skillzag.assesment.domain.Answers;
import com.org.skillzag.assesment.repository.AnswersRepository;
import com.org.skillzag.assesment.service.AnswersService;
import com.org.skillzag.assesment.service.dto.AnswersDTO;
import com.org.skillzag.assesment.service.mapper.AnswersMapper;

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
 * Integration tests for the {@link AnswersResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagassesmentApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class AnswersResourceIT {

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_IS_CORRECT = false;
    private static final Boolean UPDATED_IS_CORRECT = true;

    private static final String DEFAULT_ANSWER = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AnswersRepository answersRepository;

    @Autowired
    private AnswersMapper answersMapper;

    @Autowired
    private AnswersService answersService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswersMockMvc;

    private Answers answers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Answers createEntity(EntityManager em) {
        Answers answers = new Answers()
            .isActive(DEFAULT_IS_ACTIVE)
            .isCorrect(DEFAULT_IS_CORRECT)
            .answer(DEFAULT_ANSWER)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTime(DEFAULT_CREATED_TIME);
        return answers;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Answers createUpdatedEntity(EntityManager em) {
        Answers answers = new Answers()
            .isActive(UPDATED_IS_ACTIVE)
            .isCorrect(UPDATED_IS_CORRECT)
            .answer(UPDATED_ANSWER)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME);
        return answers;
    }

    @BeforeEach
    public void initTest() {
        answers = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnswers() throws Exception {
        int databaseSizeBeforeCreate = answersRepository.findAll().size();
        // Create the Answers
        AnswersDTO answersDTO = answersMapper.toDto(answers);
        restAnswersMockMvc.perform(post("/api/answers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(answersDTO)))
            .andExpect(status().isCreated());

        // Validate the Answers in the database
        List<Answers> answersList = answersRepository.findAll();
        assertThat(answersList).hasSize(databaseSizeBeforeCreate + 1);
        Answers testAnswers = answersList.get(answersList.size() - 1);
        assertThat(testAnswers.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testAnswers.isIsCorrect()).isEqualTo(DEFAULT_IS_CORRECT);
        assertThat(testAnswers.getAnswer()).isEqualTo(DEFAULT_ANSWER);
        assertThat(testAnswers.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAnswers.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
    }

    @Test
    @Transactional
    public void createAnswersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = answersRepository.findAll().size();

        // Create the Answers with an existing ID
        answers.setId(1L);
        AnswersDTO answersDTO = answersMapper.toDto(answers);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnswersMockMvc.perform(post("/api/answers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(answersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Answers in the database
        List<Answers> answersList = answersRepository.findAll();
        assertThat(answersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAnswers() throws Exception {
        // Initialize the database
        answersRepository.saveAndFlush(answers);

        // Get all the answersList
        restAnswersMockMvc.perform(get("/api/answers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answers.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isCorrect").value(hasItem(DEFAULT_IS_CORRECT.booleanValue())))
            .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getAnswers() throws Exception {
        // Initialize the database
        answersRepository.saveAndFlush(answers);

        // Get the answers
        restAnswersMockMvc.perform(get("/api/answers/{id}", answers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answers.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.isCorrect").value(DEFAULT_IS_CORRECT.booleanValue()))
            .andExpect(jsonPath("$.answer").value(DEFAULT_ANSWER))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTime").value(DEFAULT_CREATED_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAnswers() throws Exception {
        // Get the answers
        restAnswersMockMvc.perform(get("/api/answers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnswers() throws Exception {
        // Initialize the database
        answersRepository.saveAndFlush(answers);

        int databaseSizeBeforeUpdate = answersRepository.findAll().size();

        // Update the answers
        Answers updatedAnswers = answersRepository.findById(answers.getId()).get();
        // Disconnect from session so that the updates on updatedAnswers are not directly saved in db
        em.detach(updatedAnswers);
        updatedAnswers
            .isActive(UPDATED_IS_ACTIVE)
            .isCorrect(UPDATED_IS_CORRECT)
            .answer(UPDATED_ANSWER)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME);
        AnswersDTO answersDTO = answersMapper.toDto(updatedAnswers);

        restAnswersMockMvc.perform(put("/api/answers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(answersDTO)))
            .andExpect(status().isOk());

        // Validate the Answers in the database
        List<Answers> answersList = answersRepository.findAll();
        assertThat(answersList).hasSize(databaseSizeBeforeUpdate);
        Answers testAnswers = answersList.get(answersList.size() - 1);
        assertThat(testAnswers.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testAnswers.isIsCorrect()).isEqualTo(UPDATED_IS_CORRECT);
        assertThat(testAnswers.getAnswer()).isEqualTo(UPDATED_ANSWER);
        assertThat(testAnswers.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAnswers.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingAnswers() throws Exception {
        int databaseSizeBeforeUpdate = answersRepository.findAll().size();

        // Create the Answers
        AnswersDTO answersDTO = answersMapper.toDto(answers);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswersMockMvc.perform(put("/api/answers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(answersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Answers in the database
        List<Answers> answersList = answersRepository.findAll();
        assertThat(answersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnswers() throws Exception {
        // Initialize the database
        answersRepository.saveAndFlush(answers);

        int databaseSizeBeforeDelete = answersRepository.findAll().size();

        // Delete the answers
        restAnswersMockMvc.perform(delete("/api/answers/{id}", answers.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Answers> answersList = answersRepository.findAll();
        assertThat(answersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
