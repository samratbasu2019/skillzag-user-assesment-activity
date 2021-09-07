package com.org.skillzag.assesment.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.org.skillzag.assesment.domain.SkillzZagUserResponse} entity.
 */
public class SkillzZagUserResponseDTO implements Serializable {
    
    private Long id;

    private String userId;

    private Long questionId;

    private Long answerId;

    private Long questionSetId;

    private Instant createdTime;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
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
        if (!(o instanceof SkillzZagUserResponseDTO)) {
            return false;
        }

        return id != null && id.equals(((SkillzZagUserResponseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SkillzZagUserResponseDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", questionId=" + getQuestionId() +
            ", answerId=" + getAnswerId() +
            ", questionSetId=" + getQuestionSetId() +
            ", createdTime='" + getCreatedTime() + "'" +
            "}";
    }
}
