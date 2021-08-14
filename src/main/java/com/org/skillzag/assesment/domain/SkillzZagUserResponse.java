package com.org.skillzag.assesment.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A SkillzZagUserResponse.
 */
@Entity
@Table(name = "skillz_zag_user_response")
public class SkillzZagUserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "question_set_id")
    private Long questionSetId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public SkillzZagUserResponse userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public SkillzZagUserResponse questionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public SkillzZagUserResponse answerId(Long answerId) {
        this.answerId = answerId;
        return this;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionSetId() {
        return questionSetId;
    }

    public SkillzZagUserResponse questionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
        return this;
    }

    public void setQuestionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SkillzZagUserResponse)) {
            return false;
        }
        return id != null && id.equals(((SkillzZagUserResponse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SkillzZagUserResponse{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", questionId=" + getQuestionId() +
            ", answerId=" + getAnswerId() +
            ", questionSetId=" + getQuestionSetId() +
            "}";
    }
}
