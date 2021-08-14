package com.org.skillzag.assesment.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.org.skillzag.assesment.domain.Questions} entity.
 */
public class QuestionsDTO implements Serializable {
    
    private Long id;

    private Boolean isTrueFalse;

    private Boolean isMultiple;

    private Boolean isOrderBy;

    private Boolean isDiscussion;

    private Boolean isActive;

    private String question;

    private String createdBy;

    private Integer score;


    private Long questionSetId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsTrueFalse() {
        return isTrueFalse;
    }

    public void setIsTrueFalse(Boolean isTrueFalse) {
        this.isTrueFalse = isTrueFalse;
    }

    public Boolean isIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(Boolean isMultiple) {
        this.isMultiple = isMultiple;
    }

    public Boolean isIsOrderBy() {
        return isOrderBy;
    }

    public void setIsOrderBy(Boolean isOrderBy) {
        this.isOrderBy = isOrderBy;
    }

    public Boolean isIsDiscussion() {
        return isDiscussion;
    }

    public void setIsDiscussion(Boolean isDiscussion) {
        this.isDiscussion = isDiscussion;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionsDTO)) {
            return false;
        }

        return id != null && id.equals(((QuestionsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionsDTO{" +
            "id=" + getId() +
            ", isTrueFalse='" + isIsTrueFalse() + "'" +
            ", isMultiple='" + isIsMultiple() + "'" +
            ", isOrderBy='" + isIsOrderBy() + "'" +
            ", isDiscussion='" + isIsDiscussion() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", question='" + getQuestion() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", score=" + getScore() +
            ", questionSetId=" + getQuestionSetId() +
            "}";
    }
}
