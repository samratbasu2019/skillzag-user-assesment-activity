package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.SkillzagassesmentApp;
import com.org.skillzag.assesment.config.TestSecurityConfiguration;
import com.org.skillzag.assesment.domain.UserInCompleteTest;
import com.org.skillzag.assesment.repository.UserInCompleteTestRepository;
import com.org.skillzag.assesment.service.UserInCompleteTestService;
import com.org.skillzag.assesment.service.dto.UserInCompleteTestDTO;
import com.org.skillzag.assesment.service.mapper.UserInCompleteTestMapper;

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
 * Integration tests for the {@link UserInCompleteTestResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagassesmentApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class UserInCompleteTestResourceIT {

    private static final String DEFAULT_ANSWER = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER = "BBBBBBBBBB";

    private static final Instant DEFAULT_TEST_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TEST_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private UserInCompleteTestRepository userInCompleteTestRepository;

    @Autowired
    private UserInCompleteTestMapper userInCompleteTestMapper;

    @Autowired
    private UserInCompleteTestService userInCompleteTestService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserInCompleteTestMockMvc;

    private UserInCompleteTest userInCompleteTest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInCompleteTest createEntity(EntityManager em) {
        UserInCompleteTest userInCompleteTest = new UserInCompleteTest()
            .answer(DEFAULT_ANSWER)
            .testTime(DEFAULT_TEST_TIME);
        return userInCompleteTest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInCompleteTest createUpdatedEntity(EntityManager em) {
        UserInCompleteTest userInCompleteTest = new UserInCompleteTest()
            .answer(UPDATED_ANSWER)
            .testTime(UPDATED_TEST_TIME);
        return userInCompleteTest;
    }

    @BeforeEach
    public void initTest() {
        userInCompleteTest = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserInCompleteTest() throws Exception {
        int databaseSizeBeforeCreate = userInCompleteTestRepository.findAll().size();
        // Create the UserInCompleteTest
        UserInCompleteTestDTO userInCompleteTestDTO = userInCompleteTestMapper.toDto(userInCompleteTest);
        restUserInCompleteTestMockMvc.perform(post("/api/user-in-complete-tests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInCompleteTestDTO)))
            .andExpect(status().isCreated());

        // Validate the UserInCompleteTest in the database
        List<UserInCompleteTest> userInCompleteTestList = userInCompleteTestRepository.findAll();
        assertThat(userInCompleteTestList).hasSize(databaseSizeBeforeCreate + 1);
        UserInCompleteTest testUserInCompleteTest = userInCompleteTestList.get(userInCompleteTestList.size() - 1);
        assertThat(testUserInCompleteTest.getAnswer()).isEqualTo(DEFAULT_ANSWER);
        assertThat(testUserInCompleteTest.getTestTime()).isEqualTo(DEFAULT_TEST_TIME);
    }

    @Test
    @Transactional
    public void createUserInCompleteTestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userInCompleteTestRepository.findAll().size();

        // Create the UserInCompleteTest with an existing ID
        userInCompleteTest.setId(1L);
        UserInCompleteTestDTO userInCompleteTestDTO = userInCompleteTestMapper.toDto(userInCompleteTest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserInCompleteTestMockMvc.perform(post("/api/user-in-complete-tests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInCompleteTestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserInCompleteTest in the database
        List<UserInCompleteTest> userInCompleteTestList = userInCompleteTestRepository.findAll();
        assertThat(userInCompleteTestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserInCompleteTests() throws Exception {
        // Initialize the database
        userInCompleteTestRepository.saveAndFlush(userInCompleteTest);

        // Get all the userInCompleteTestList
        restUserInCompleteTestMockMvc.perform(get("/api/user-in-complete-tests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userInCompleteTest.getId().intValue())))
            .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER)))
            .andExpect(jsonPath("$.[*].testTime").value(hasItem(DEFAULT_TEST_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getUserInCompleteTest() throws Exception {
        // Initialize the database
        userInCompleteTestRepository.saveAndFlush(userInCompleteTest);

        // Get the userInCompleteTest
        restUserInCompleteTestMockMvc.perform(get("/api/user-in-complete-tests/{id}", userInCompleteTest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userInCompleteTest.getId().intValue()))
            .andExpect(jsonPath("$.answer").value(DEFAULT_ANSWER))
            .andExpect(jsonPath("$.testTime").value(DEFAULT_TEST_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUserInCompleteTest() throws Exception {
        // Get the userInCompleteTest
        restUserInCompleteTestMockMvc.perform(get("/api/user-in-complete-tests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserInCompleteTest() throws Exception {
        // Initialize the database
        userInCompleteTestRepository.saveAndFlush(userInCompleteTest);

        int databaseSizeBeforeUpdate = userInCompleteTestRepository.findAll().size();

        // Update the userInCompleteTest
        UserInCompleteTest updatedUserInCompleteTest = userInCompleteTestRepository.findById(userInCompleteTest.getId()).get();
        // Disconnect from session so that the updates on updatedUserInCompleteTest are not directly saved in db
        em.detach(updatedUserInCompleteTest);
        updatedUserInCompleteTest
            .answer(UPDATED_ANSWER)
            .testTime(UPDATED_TEST_TIME);
        UserInCompleteTestDTO userInCompleteTestDTO = userInCompleteTestMapper.toDto(updatedUserInCompleteTest);

        restUserInCompleteTestMockMvc.perform(put("/api/user-in-complete-tests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInCompleteTestDTO)))
            .andExpect(status().isOk());

        // Validate the UserInCompleteTest in the database
        List<UserInCompleteTest> userInCompleteTestList = userInCompleteTestRepository.findAll();
        assertThat(userInCompleteTestList).hasSize(databaseSizeBeforeUpdate);
        UserInCompleteTest testUserInCompleteTest = userInCompleteTestList.get(userInCompleteTestList.size() - 1);
        assertThat(testUserInCompleteTest.getAnswer()).isEqualTo(UPDATED_ANSWER);
        assertThat(testUserInCompleteTest.getTestTime()).isEqualTo(UPDATED_TEST_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingUserInCompleteTest() throws Exception {
        int databaseSizeBeforeUpdate = userInCompleteTestRepository.findAll().size();

        // Create the UserInCompleteTest
        UserInCompleteTestDTO userInCompleteTestDTO = userInCompleteTestMapper.toDto(userInCompleteTest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserInCompleteTestMockMvc.perform(put("/api/user-in-complete-tests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInCompleteTestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserInCompleteTest in the database
        List<UserInCompleteTest> userInCompleteTestList = userInCompleteTestRepository.findAll();
        assertThat(userInCompleteTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserInCompleteTest() throws Exception {
        // Initialize the database
        userInCompleteTestRepository.saveAndFlush(userInCompleteTest);

        int databaseSizeBeforeDelete = userInCompleteTestRepository.findAll().size();

        // Delete the userInCompleteTest
        restUserInCompleteTestMockMvc.perform(delete("/api/user-in-complete-tests/{id}", userInCompleteTest.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserInCompleteTest> userInCompleteTestList = userInCompleteTestRepository.findAll();
        assertThat(userInCompleteTestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
