package com.org.skillzag.assesment.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A QuestionSet.
 */
@Entity
@Table(name = "question_set")
public class QuestionSet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "question_set")
    private String questionSet;

    @Column(name = "score")
    private Long score;

    @Column(name = "type")
    private String type;

    @Column(name = "question_set_type")
    private String questionSetType;

    @Column(name = "number_of_questions")
    private Integer numberOfQuestions;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private Instant createdTime;

    @OneToMany(mappedBy = "questionSet")
    private Set<Questions> questions = new HashSet<>();

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

    public QuestionSet isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getQuestionSet() {
        return questionSet;
    }

    public QuestionSet questionSet(String questionSet) {
        this.questionSet = questionSet;
        return this;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public Long getScore() {
        return score;
    }

    public QuestionSet score(Long score) {
        this.score = score;
        return this;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public QuestionSet type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestionSetType() {
        return questionSetType;
    }

    public QuestionSet questionSetType(String questionSetType) {
        this.questionSetType = questionSetType;
        return this;
    }

    public void setQuestionSetType(String questionSetType) {
        this.questionSetType = questionSetType;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public QuestionSet numberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        return this;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public QuestionSet createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public QuestionSet createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Set<Questions> getQuestions() {
        return questions;
    }

    public QuestionSet questions(Set<Questions> questions) {
        this.questions = questions;
        return this;
    }

    public QuestionSet addQuestions(Questions questions) {
        this.questions.add(questions);
        questions.setQuestionSet(this);
        return this;
    }

    public QuestionSet removeQuestions(Questions questions) {
        this.questions.remove(questions);
        questions.setQuestionSet(null);
        return this;
    }

    public void setQuestions(Set<Questions> questions) {
        this.questions = questions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSet)) {
            return false;
        }
        return id != null && id.equals(((QuestionSet) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSet{" +
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
