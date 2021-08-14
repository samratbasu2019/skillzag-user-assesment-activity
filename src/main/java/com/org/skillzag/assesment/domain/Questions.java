package com.org.skillzag.assesment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Questions.
 */
@Entity
@Table(name = "questions")
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_true_false")
    private Boolean isTrueFalse;

    @Column(name = "is_multiple")
    private Boolean isMultiple;

    @Column(name = "is_order_by")
    private Boolean isOrderBy;

    @Column(name = "is_discussion")
    private Boolean isDiscussion;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "question")
    private String question;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "score")
    private Integer score;

    @OneToMany(mappedBy = "questions")
    private Set<Answers> answers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "questions", allowSetters = true)
    private QuestionSet questionSet;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsTrueFalse() {
        return isTrueFalse;
    }

    public Questions isTrueFalse(Boolean isTrueFalse) {
        this.isTrueFalse = isTrueFalse;
        return this;
    }

    public void setIsTrueFalse(Boolean isTrueFalse) {
        this.isTrueFalse = isTrueFalse;
    }

    public Boolean isIsMultiple() {
        return isMultiple;
    }

    public Questions isMultiple(Boolean isMultiple) {
        this.isMultiple = isMultiple;
        return this;
    }

    public void setIsMultiple(Boolean isMultiple) {
        this.isMultiple = isMultiple;
    }

    public Boolean isIsOrderBy() {
        return isOrderBy;
    }

    public Questions isOrderBy(Boolean isOrderBy) {
        this.isOrderBy = isOrderBy;
        return this;
    }

    public void setIsOrderBy(Boolean isOrderBy) {
        this.isOrderBy = isOrderBy;
    }

    public Boolean isIsDiscussion() {
        return isDiscussion;
    }

    public Questions isDiscussion(Boolean isDiscussion) {
        this.isDiscussion = isDiscussion;
        return this;
    }

    public void setIsDiscussion(Boolean isDiscussion) {
        this.isDiscussion = isDiscussion;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Questions isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getQuestion() {
        return question;
    }

    public Questions question(String question) {
        this.question = question;
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Questions createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getScore() {
        return score;
    }

    public Questions score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Set<Answers> getAnswers() {
        return answers;
    }

    public Questions answers(Set<Answers> answers) {
        this.answers = answers;
        return this;
    }

    public Questions addAnswers(Answers answers) {
        this.answers.add(answers);
        answers.setQuestions(this);
        return this;
    }

    public Questions removeAnswers(Answers answers) {
        this.answers.remove(answers);
        answers.setQuestions(null);
        return this;
    }

    public void setAnswers(Set<Answers> answers) {
        this.answers = answers;
    }

    public QuestionSet getQuestionSet() {
        return questionSet;
    }

    public Questions questionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
        return this;
    }

    public void setQuestionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Questions)) {
            return false;
        }
        return id != null && id.equals(((Questions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Questions{" +
            "id=" + getId() +
            ", isTrueFalse='" + isIsTrueFalse() + "'" +
            ", isMultiple='" + isIsMultiple() + "'" +
            ", isOrderBy='" + isIsOrderBy() + "'" +
            ", isDiscussion='" + isIsDiscussion() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", question='" + getQuestion() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", score=" + getScore() +
            "}";
    }
}
