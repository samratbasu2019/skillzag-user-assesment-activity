package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.SkillzagassesmentApp;
import com.org.skillzag.assesment.config.TestSecurityConfiguration;
import com.org.skillzag.assesment.domain.UserCompleteTest;
import com.org.skillzag.assesment.repository.UserCompleteTestRepository;
import com.org.skillzag.assesment.service.UserCompleteTestService;
import com.org.skillzag.assesment.service.dto.UserCompleteTestDTO;
import com.org.skillzag.assesment.service.mapper.UserCompleteTestMapper;

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
 * Integration tests for the {@link UserCompleteTestResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagassesmentApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class UserCompleteTestResourceIT {

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Instant DEFAULT_TEST_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TEST_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TEST_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TEST_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_TEST_DURATION = 1;
    private static final Integer UPDATED_TEST_DURATION = 2;

    private static final Integer DEFAULT_TEST_MAX_SCORE = 1;
    private static final Integer UPDATED_TEST_MAX_SCORE = 2;

    private static final Integer DEFAULT_TEST_SCORE = 1;
    private static final Integer UPDATED_TEST_SCORE = 2;

    private static final Integer DEFAULT_TEST_ANSWERED = 1;
    private static final Integer UPDATED_TEST_ANSWERED = 2;

    private static final Integer DEFAULT_TEST_UNANSWERED = 1;
    private static final Integer UPDATED_TEST_UNANSWERED = 2;

    @Autowired
    private UserCompleteTestRepository userCompleteTestRepository;

    @Autowired
    private UserCompleteTestMapper userCompleteTestMapper;

    @Autowired
    private UserCompleteTestService userCompleteTestService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserCompleteTestMockMvc;

    private UserCompleteTest userCompleteTest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserCompleteTest createEntity(EntityManager em) {
        UserCompleteTest userCompleteTest = new UserCompleteTest()
            .isActive(DEFAULT_IS_ACTIVE)
            .testDate(DEFAULT_TEST_DATE)
            .testTime(DEFAULT_TEST_TIME)
            .testDuration(DEFAULT_TEST_DURATION)
            .testMaxScore(DEFAULT_TEST_MAX_SCORE)
            .testScore(DEFAULT_TEST_SCORE)
            .testAnswered(DEFAULT_TEST_ANSWERED)
            .testUnanswered(DEFAULT_TEST_UNANSWERED);
        return userCompleteTest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserCompleteTest createUpdatedEntity(EntityManager em) {
        UserCompleteTest userCompleteTest = new UserCompleteTest()
            .isActive(UPDATED_IS_ACTIVE)
            .testDate(UPDATED_TEST_DATE)
            .testTime(UPDATED_TEST_TIME)
            .testDuration(UPDATED_TEST_DURATION)
            .testMaxScore(UPDATED_TEST_MAX_SCORE)
            .testScore(UPDATED_TEST_SCORE)
            .testAnswered(UPDATED_TEST_ANSWERED)
            .testUnanswered(UPDATED_TEST_UNANSWERED);
        return userCompleteTest;
    }

    @BeforeEach
    public void initTest() {
        userCompleteTest = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserCompleteTest() throws Exception {
        int databaseSizeBeforeCreate = userCompleteTestRepository.findAll().size();
        // Create the UserCompleteTest
        UserCompleteTestDTO userCompleteTestDTO = userCompleteTestMapper.toDto(userCompleteTest);
        restUserCompleteTestMockMvc.perform(post("/api/user-complete-tests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompleteTestDTO)))
            .andExpect(status().isCreated());

        // Validate the UserCompleteTest in the database
        List<UserCompleteTest> userCompleteTestList = userCompleteTestRepository.findAll();
        assertThat(userCompleteTestList).hasSize(databaseSizeBeforeCreate + 1);
        UserCompleteTest testUserCompleteTest = userCompleteTestList.get(userCompleteTestList.size() - 1);
        assertThat(testUserCompleteTest.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testUserCompleteTest.getTestDate()).isEqualTo(DEFAULT_TEST_DATE);
        assertThat(testUserCompleteTest.getTestTime()).isEqualTo(DEFAULT_TEST_TIME);
        assertThat(testUserCompleteTest.getTestDuration()).isEqualTo(DEFAULT_TEST_DURATION);
        assertThat(testUserCompleteTest.getTestMaxScore()).isEqualTo(DEFAULT_TEST_MAX_SCORE);
        assertThat(testUserCompleteTest.getTestScore()).isEqualTo(DEFAULT_TEST_SCORE);
        assertThat(testUserCompleteTest.getTestAnswered()).isEqualTo(DEFAULT_TEST_ANSWERED);
        assertThat(testUserCompleteTest.getTestUnanswered()).isEqualTo(DEFAULT_TEST_UNANSWERED);
    }

    @Test
    @Transactional
    public void createUserCompleteTestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userCompleteTestRepository.findAll().size();

        // Create the UserCompleteTest with an existing ID
        userCompleteTest.setId(1L);
        UserCompleteTestDTO userCompleteTestDTO = userCompleteTestMapper.toDto(userCompleteTest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCompleteTestMockMvc.perform(post("/api/user-complete-tests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompleteTestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserCompleteTest in the database
        List<UserCompleteTest> userCompleteTestList = userCompleteTestRepository.findAll();
        assertThat(userCompleteTestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserCompleteTests() throws Exception {
        // Initialize the database
        userCompleteTestRepository.saveAndFlush(userCompleteTest);

        // Get all the userCompleteTestList
        restUserCompleteTestMockMvc.perform(get("/api/user-complete-tests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCompleteTest.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].testDate").value(hasItem(DEFAULT_TEST_DATE.toString())))
            .andExpect(jsonPath("$.[*].testTime").value(hasItem(DEFAULT_TEST_TIME.toString())))
            .andExpect(jsonPath("$.[*].testDuration").value(hasItem(DEFAULT_TEST_DURATION)))
            .andExpect(jsonPath("$.[*].testMaxScore").value(hasItem(DEFAULT_TEST_MAX_SCORE)))
            .andExpect(jsonPath("$.[*].testScore").value(hasItem(DEFAULT_TEST_SCORE)))
            .andExpect(jsonPath("$.[*].testAnswered").value(hasItem(DEFAULT_TEST_ANSWERED)))
            .andExpect(jsonPath("$.[*].testUnanswered").value(hasItem(DEFAULT_TEST_UNANSWERED)));
    }
    
    @Test
    @Transactional
    public void getUserCompleteTest() throws Exception {
        // Initialize the database
        userCompleteTestRepository.saveAndFlush(userCompleteTest);

        // Get the userCompleteTest
        restUserCompleteTestMockMvc.perform(get("/api/user-complete-tests/{id}", userCompleteTest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userCompleteTest.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.testDate").value(DEFAULT_TEST_DATE.toString()))
            .andExpect(jsonPath("$.testTime").value(DEFAULT_TEST_TIME.toString()))
            .andExpect(jsonPath("$.testDuration").value(DEFAULT_TEST_DURATION))
            .andExpect(jsonPath("$.testMaxScore").value(DEFAULT_TEST_MAX_SCORE))
            .andExpect(jsonPath("$.testScore").value(DEFAULT_TEST_SCORE))
            .andExpect(jsonPath("$.testAnswered").value(DEFAULT_TEST_ANSWERED))
            .andExpect(jsonPath("$.testUnanswered").value(DEFAULT_TEST_UNANSWERED));
    }
    @Test
    @Transactional
    public void getNonExistingUserCompleteTest() throws Exception {
        // Get the userCompleteTest
        restUserCompleteTestMockMvc.perform(get("/api/user-complete-tests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserCompleteTest() throws Exception {
        // Initialize the database
        userCompleteTestRepository.saveAndFlush(userCompleteTest);

        int databaseSizeBeforeUpdate = userCompleteTestRepository.findAll().size();

        // Update the userCompleteTest
        UserCompleteTest updatedUserCompleteTest = userCompleteTestRepository.findById(userCompleteTest.getId()).get();
        // Disconnect from session so that the updates on updatedUserCompleteTest are not directly saved in db
        em.detach(updatedUserCompleteTest);
        updatedUserCompleteTest
            .isActive(UPDATED_IS_ACTIVE)
            .testDate(UPDATED_TEST_DATE)
            .testTime(UPDATED_TEST_TIME)
            .testDuration(UPDATED_TEST_DURATION)
            .testMaxScore(UPDATED_TEST_MAX_SCORE)
            .testScore(UPDATED_TEST_SCORE)
            .testAnswered(UPDATED_TEST_ANSWERED)
            .testUnanswered(UPDATED_TEST_UNANSWERED);
        UserCompleteTestDTO userCompleteTestDTO = userCompleteTestMapper.toDto(updatedUserCompleteTest);

        restUserCompleteTestMockMvc.perform(put("/api/user-complete-tests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompleteTestDTO)))
            .andExpect(status().isOk());

        // Validate the UserCompleteTest in the database
        List<UserCompleteTest> userCompleteTestList = userCompleteTestRepository.findAll();
        assertThat(userCompleteTestList).hasSize(databaseSizeBeforeUpdate);
        UserCompleteTest testUserCompleteTest = userCompleteTestList.get(userCompleteTestList.size() - 1);
        assertThat(testUserCompleteTest.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testUserCompleteTest.getTestDate()).isEqualTo(UPDATED_TEST_DATE);
        assertThat(testUserCompleteTest.getTestTime()).isEqualTo(UPDATED_TEST_TIME);
        assertThat(testUserCompleteTest.getTestDuration()).isEqualTo(UPDATED_TEST_DURATION);
        assertThat(testUserCompleteTest.getTestMaxScore()).isEqualTo(UPDATED_TEST_MAX_SCORE);
        assertThat(testUserCompleteTest.getTestScore()).isEqualTo(UPDATED_TEST_SCORE);
        assertThat(testUserCompleteTest.getTestAnswered()).isEqualTo(UPDATED_TEST_ANSWERED);
        assertThat(testUserCompleteTest.getTestUnanswered()).isEqualTo(UPDATED_TEST_UNANSWERED);
    }

    @Test
    @Transactional
    public void updateNonExistingUserCompleteTest() throws Exception {
        int databaseSizeBeforeUpdate = userCompleteTestRepository.findAll().size();

        // Create the UserCompleteTest
        UserCompleteTestDTO userCompleteTestDTO = userCompleteTestMapper.toDto(userCompleteTest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserCompleteTestMockMvc.perform(put("/api/user-complete-tests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompleteTestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserCompleteTest in the database
        List<UserCompleteTest> userCompleteTestList = userCompleteTestRepository.findAll();
        assertThat(userCompleteTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserCompleteTest() throws Exception {
        // Initialize the database
        userCompleteTestRepository.saveAndFlush(userCompleteTest);

        int databaseSizeBeforeDelete = userCompleteTestRepository.findAll().size();

        // Delete the userCompleteTest
        restUserCompleteTestMockMvc.perform(delete("/api/user-complete-tests/{id}", userCompleteTest.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserCompleteTest> userCompleteTestList = userCompleteTestRepository.findAll();
        assertThat(userCompleteTestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
