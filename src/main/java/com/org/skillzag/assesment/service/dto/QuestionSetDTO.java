package com.org.skillzag.assesment.service.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.org.skillzag.assesment.domain.QuestionSet} entity.
 */
public class QuestionSetDTO implements Serializable {

    private Long id;

    private Boolean isActive;

    private String questionSet;

    private Long score;

    private String type;

    private String questionSetType;

    private Integer numberOfQuestions;

    private String createdBy;

    private Instant createdTime;


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

    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestionSetType() {
        return questionSetType;
    }

    public void setQuestionSetType(String questionSetType) {
        this.questionSetType = questionSetType;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSetDTO)) {
            return false;
        }

        return id != null && id.equals(((QuestionSetDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSetDTO{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", questionSet='" + getQuestionSet() + "'" +
            ", score=" + getScore() +
            ", type='" + getType() + "'" +
            ", questionSetType='" + getQuestionSetType() + "'" +
            ", numberOfQuestions=" + getNumberOfQuestions() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            "}";
    }
}
