package com.org.skillzag.assesment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A UserCompleteTest.
 */
@Entity
@Table(name = "user_complete_test")
public class UserCompleteTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "test_date")
    private Instant testDate;

    @Column(name = "test_time")
    private Instant testTime;

    @Column(name = "test_duration")
    private Integer testDuration;

    @Column(name = "test_max_score")
    private Integer testMaxScore;

    @Column(name = "test_score")
    private Integer testScore;

    @Column(name = "test_answered")
    private Integer testAnswered;

    @Column(name = "test_unanswered")
    private Integer testUnanswered;

    @ManyToOne
    @JsonIgnoreProperties(value = "userCompleteTests", allowSetters = true)
    private SkillZagUser skillZagUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public UserCompleteTest isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getTestDate() {
        return testDate;
    }

    public UserCompleteTest testDate(Instant testDate) {
        this.testDate = testDate;
        return this;
    }

    public void setTestDate(Instant testDate) {
        this.testDate = testDate;
    }

    public Instant getTestTime() {
        return testTime;
    }

    public UserCompleteTest testTime(Instant testTime) {
        this.testTime = testTime;
        return this;
    }

    public void setTestTime(Instant testTime) {
        this.testTime = testTime;
    }

    public Integer getTestDuration() {
        return testDuration;
    }

    public UserCompleteTest testDuration(Integer testDuration) {
        this.testDuration = testDuration;
        return this;
    }

    public void setTestDuration(Integer testDuration) {
        this.testDuration = testDuration;
    }

    public Integer getTestMaxScore() {
        return testMaxScore;
    }

    public UserCompleteTest testMaxScore(Integer testMaxScore) {
        this.testMaxScore = testMaxScore;
        return this;
    }

    public void setTestMaxScore(Integer testMaxScore) {
        this.testMaxScore = testMaxScore;
    }

    public Integer getTestScore() {
        return testScore;
    }

    public UserCompleteTest testScore(Integer testScore) {
        this.testScore = testScore;
        return this;
    }

    public void setTestScore(Integer testScore) {
        this.testScore = testScore;
    }

    public Integer getTestAnswered() {
        return testAnswered;
    }

    public UserCompleteTest testAnswered(Integer testAnswered) {
        this.testAnswered = testAnswered;
        return this;
    }

    public void setTestAnswered(Integer testAnswered) {
        this.testAnswered = testAnswered;
    }

    public Integer getTestUnanswered() {
        return testUnanswered;
    }

    public UserCompleteTest testUnanswered(Integer testUnanswered) {
        this.testUnanswered = testUnanswered;
        return this;
    }

    public void setTestUnanswered(Integer testUnanswered) {
        this.testUnanswered = testUnanswered;
    }

    public SkillZagUser getSkillZagUser() {
        return skillZagUser;
    }

    public UserCompleteTest skillZagUser(SkillZagUser skillZagUser) {
        this.skillZagUser = skillZagUser;
        return this;
    }

    public void setSkillZagUser(SkillZagUser skillZagUser) {
        this.skillZagUser = skillZagUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCompleteTest)) {
            return false;
        }
        return id != null && id.equals(((UserCompleteTest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCompleteTest{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", testDate='" + getTestDate() + "'" +
            ", testTime='" + getTestTime() + "'" +
            ", testDuration=" + getTestDuration() +
            ", testMaxScore=" + getTestMaxScore() +
            ", testScore=" + getTestScore() +
            ", testAnswered=" + getTestAnswered() +
            ", testUnanswered=" + getTestUnanswered() +
            "}";
    }
}
