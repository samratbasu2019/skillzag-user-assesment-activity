package com.org.skillzag.assesment.service.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.org.skillzag.assesment.domain.UserCompleteTest} entity.
 */
public class UserCompleteTestDTO implements Serializable {

    private Long id;

    private Boolean isActive;

    private Instant testDate;

    private Instant testTime;

    private Integer testDuration;

    private Integer testMaxScore;

    private Integer testScore;

    private Integer testAnswered;

    private Integer testUnanswered;


    private Long skillZagUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getTestDate() {
        return testDate;
    }

    public void setTestDate(Instant testDate) {
        this.testDate = testDate;
    }

    public Instant getTestTime() {
        return testTime;
    }

    public void setTestTime(Instant testTime) {
        this.testTime = testTime;
    }

    public Integer getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(Integer testDuration) {
        this.testDuration = testDuration;
    }

    public Integer getTestMaxScore() {
        return testMaxScore;
    }

    public void setTestMaxScore(Integer testMaxScore) {
        this.testMaxScore = testMaxScore;
    }

    public Integer getTestScore() {
        return testScore;
    }

    public void setTestScore(Integer testScore) {
        this.testScore = testScore;
    }

    public Integer getTestAnswered() {
        return testAnswered;
    }

    public void setTestAnswered(Integer testAnswered) {
        this.testAnswered = testAnswered;
    }

    public Integer getTestUnanswered() {
        return testUnanswered;
    }

    public void setTestUnanswered(Integer testUnanswered) {
        this.testUnanswered = testUnanswered;
    }

    public Long getSkillZagUserId() {
        return skillZagUserId;
    }

    public void setSkillZagUserId(Long skillZagUserId) {
        this.skillZagUserId = skillZagUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCompleteTestDTO)) {
            return false;
        }

        return id != null && id.equals(((UserCompleteTestDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCompleteTestDTO{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", testDate='" + getTestDate() + "'" +
            ", testTime='" + getTestTime() + "'" +
            ", testDuration=" + getTestDuration() +
            ", testMaxScore=" + getTestMaxScore() +
            ", testScore=" + getTestScore() +
            ", testAnswered=" + getTestAnswered() +
            ", testUnanswered=" + getTestUnanswered() +
            ", skillZagUserId=" + getSkillZagUserId() +
            "}";
    }
}
