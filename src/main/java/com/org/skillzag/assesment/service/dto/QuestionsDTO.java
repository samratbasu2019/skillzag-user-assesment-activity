package com.org.skillzag.assesment.service.dto;

import java.time.Instant;
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

    private String videoUrl;

    private String imageUrl;

    private Boolean isActive;

    private String question;

    private String createdBy;

    private Instant createdTime;

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
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
            ", videoUrl='" + getVideoUrl() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", question='" + getQuestion() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", score=" + getScore() +
            ", questionSetId=" + getQuestionSetId() +
            "}";
    }
}
