package com.org.skillzag.assesment.web.rest;

import com.org.skillzag.assesment.SkillzagassesmentApp;
import com.org.skillzag.assesment.config.TestSecurityConfiguration;
import com.org.skillzag.assesment.domain.SkillZagUser;
import com.org.skillzag.assesment.repository.SkillZagUserRepository;
import com.org.skillzag.assesment.service.SkillZagUserService;
import com.org.skillzag.assesment.service.dto.SkillZagUserDTO;
import com.org.skillzag.assesment.service.mapper.SkillZagUserMapper;

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
 * Integration tests for the {@link SkillZagUserResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagassesmentApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class SkillZagUserResourceIT {

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_USER_ROLE = "BBBBBBBBBB";

    private static final Long DEFAULT_QUESTION_SET_ID = 1L;
    private static final Long UPDATED_QUESTION_SET_ID = 2L;

    private static final String DEFAULT_OTHERS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERS = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_COMPLETE_TEST = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_COMPLETE_TEST = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_IN_COMPLETE_TEST = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_IN_COMPLETE_TEST = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SkillZagUserRepository skillZagUserRepository;

    @Autowired
    private SkillZagUserMapper skillZagUserMapper;

    @Autowired
    private SkillZagUserService skillZagUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSkillZagUserMockMvc;

    private SkillZagUser skillZagUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillZagUser createEntity(EntityManager em) {
        SkillZagUser skillZagUser = new SkillZagUser()
            .isActive(DEFAULT_IS_ACTIVE)
            .userName(DEFAULT_USER_NAME)
            .userRole(DEFAULT_USER_ROLE)
            .questionSetId(DEFAULT_QUESTION_SET_ID)
            .others(DEFAULT_OTHERS)
            .lastCompleteTest(DEFAULT_LAST_COMPLETE_TEST)
            .lastInCompleteTest(DEFAULT_LAST_IN_COMPLETE_TEST);
        return skillZagUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillZagUser createUpdatedEntity(EntityManager em) {
        SkillZagUser skillZagUser = new SkillZagUser()
            .isActive(UPDATED_IS_ACTIVE)
            .userName(UPDATED_USER_NAME)
            .userRole(UPDATED_USER_ROLE)
            .questionSetId(UPDATED_QUESTION_SET_ID)
            .others(UPDATED_OTHERS)
            .lastCompleteTest(UPDATED_LAST_COMPLETE_TEST)
            .lastInCompleteTest(UPDATED_LAST_IN_COMPLETE_TEST);
        return skillZagUser;
    }

    @BeforeEach
    public void initTest() {
        skillZagUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkillZagUser() throws Exception {
        int databaseSizeBeforeCreate = skillZagUserRepository.findAll().size();
        // Create the SkillZagUser
        SkillZagUserDTO skillZagUserDTO = skillZagUserMapper.toDto(skillZagUser);
        restSkillZagUserMockMvc.perform(post("/api/skill-zag-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillZagUserDTO)))
            .andExpect(status().isCreated());

        // Validate the SkillZagUser in the database
        List<SkillZagUser> skillZagUserList = skillZagUserRepository.findAll();
        assertThat(skillZagUserList).hasSize(databaseSizeBeforeCreate + 1);
        SkillZagUser testSkillZagUser = skillZagUserList.get(skillZagUserList.size() - 1);
        assertThat(testSkillZagUser.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testSkillZagUser.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testSkillZagUser.getUserRole()).isEqualTo(DEFAULT_USER_ROLE);
        assertThat(testSkillZagUser.getQuestionSetId()).isEqualTo(DEFAULT_QUESTION_SET_ID);
        assertThat(testSkillZagUser.getOthers()).isEqualTo(DEFAULT_OTHERS);
        assertThat(testSkillZagUser.getLastCompleteTest()).isEqualTo(DEFAULT_LAST_COMPLETE_TEST);
        assertThat(testSkillZagUser.getLastInCompleteTest()).isEqualTo(DEFAULT_LAST_IN_COMPLETE_TEST);
    }

    @Test
    @Transactional
    public void createSkillZagUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skillZagUserRepository.findAll().size();

        // Create the SkillZagUser with an existing ID
        skillZagUser.setId(1L);
        SkillZagUserDTO skillZagUserDTO = skillZagUserMapper.toDto(skillZagUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillZagUserMockMvc.perform(post("/api/skill-zag-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillZagUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SkillZagUser in the database
        List<SkillZagUser> skillZagUserList = skillZagUserRepository.findAll();
        assertThat(skillZagUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSkillZagUsers() throws Exception {
        // Initialize the database
        skillZagUserRepository.saveAndFlush(skillZagUser);

        // Get all the skillZagUserList
        restSkillZagUserMockMvc.perform(get("/api/skill-zag-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skillZagUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].userRole").value(hasItem(DEFAULT_USER_ROLE)))
            .andExpect(jsonPath("$.[*].questionSetId").value(hasItem(DEFAULT_QUESTION_SET_ID.intValue())))
            .andExpect(jsonPath("$.[*].others").value(hasItem(DEFAULT_OTHERS)))
            .andExpect(jsonPath("$.[*].lastCompleteTest").value(hasItem(DEFAULT_LAST_COMPLETE_TEST.toString())))
            .andExpect(jsonPath("$.[*].lastInCompleteTest").value(hasItem(DEFAULT_LAST_IN_COMPLETE_TEST.toString())));
    }
    
    @Test
    @Transactional
    public void getSkillZagUser() throws Exception {
        // Initialize the database
        skillZagUserRepository.saveAndFlush(skillZagUser);

        // Get the skillZagUser
        restSkillZagUserMockMvc.perform(get("/api/skill-zag-users/{id}", skillZagUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(skillZagUser.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.userRole").value(DEFAULT_USER_ROLE))
            .andExpect(jsonPath("$.questionSetId").value(DEFAULT_QUESTION_SET_ID.intValue()))
            .andExpect(jsonPath("$.others").value(DEFAULT_OTHERS))
            .andExpect(jsonPath("$.lastCompleteTest").value(DEFAULT_LAST_COMPLETE_TEST.toString()))
            .andExpect(jsonPath("$.lastInCompleteTest").value(DEFAULT_LAST_IN_COMPLETE_TEST.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSkillZagUser() throws Exception {
        // Get the skillZagUser
        restSkillZagUserMockMvc.perform(get("/api/skill-zag-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkillZagUser() throws Exception {
        // Initialize the database
        skillZagUserRepository.saveAndFlush(skillZagUser);

        int databaseSizeBeforeUpdate = skillZagUserRepository.findAll().size();

        // Update the skillZagUser
        SkillZagUser updatedSkillZagUser = skillZagUserRepository.findById(skillZagUser.getId()).get();
        // Disconnect from session so that the updates on updatedSkillZagUser are not directly saved in db
        em.detach(updatedSkillZagUser);
        updatedSkillZagUser
            .isActive(UPDATED_IS_ACTIVE)
            .userName(UPDATED_USER_NAME)
            .userRole(UPDATED_USER_ROLE)
            .questionSetId(UPDATED_QUESTION_SET_ID)
            .others(UPDATED_OTHERS)
            .lastCompleteTest(UPDATED_LAST_COMPLETE_TEST)
            .lastInCompleteTest(UPDATED_LAST_IN_COMPLETE_TEST);
        SkillZagUserDTO skillZagUserDTO = skillZagUserMapper.toDto(updatedSkillZagUser);

        restSkillZagUserMockMvc.perform(put("/api/skill-zag-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillZagUserDTO)))
            .andExpect(status().isOk());

        // Validate the SkillZagUser in the database
        List<SkillZagUser> skillZagUserList = skillZagUserRepository.findAll();
        assertThat(skillZagUserList).hasSize(databaseSizeBeforeUpdate);
        SkillZagUser testSkillZagUser = skillZagUserList.get(skillZagUserList.size() - 1);
        assertThat(testSkillZagUser.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSkillZagUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testSkillZagUser.getUserRole()).isEqualTo(UPDATED_USER_ROLE);
        assertThat(testSkillZagUser.getQuestionSetId()).isEqualTo(UPDATED_QUESTION_SET_ID);
        assertThat(testSkillZagUser.getOthers()).isEqualTo(UPDATED_OTHERS);
        assertThat(testSkillZagUser.getLastCompleteTest()).isEqualTo(UPDATED_LAST_COMPLETE_TEST);
        assertThat(testSkillZagUser.getLastInCompleteTest()).isEqualTo(UPDATED_LAST_IN_COMPLETE_TEST);
    }

    @Test
    @Transactional
    public void updateNonExistingSkillZagUser() throws Exception {
        int databaseSizeBeforeUpdate = skillZagUserRepository.findAll().size();

        // Create the SkillZagUser
        SkillZagUserDTO skillZagUserDTO = skillZagUserMapper.toDto(skillZagUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkillZagUserMockMvc.perform(put("/api/skill-zag-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillZagUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SkillZagUser in the database
        List<SkillZagUser> skillZagUserList = skillZagUserRepository.findAll();
        assertThat(skillZagUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSkillZagUser() throws Exception {
        // Initialize the database
        skillZagUserRepository.saveAndFlush(skillZagUser);

        int databaseSizeBeforeDelete = skillZagUserRepository.findAll().size();

        // Delete the skillZagUser
        restSkillZagUserMockMvc.perform(delete("/api/skill-zag-users/{id}", skillZagUser.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SkillZagUser> skillZagUserList = skillZagUserRepository.findAll();
        assertThat(skillZagUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
