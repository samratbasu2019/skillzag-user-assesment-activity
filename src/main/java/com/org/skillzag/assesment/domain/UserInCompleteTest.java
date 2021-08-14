package com.org.skillzag.assesment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A UserInCompleteTest.
 */
@Entity
@Table(name = "user_in_complete_test")
public class UserInCompleteTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "answer")
    private String answer;

    @Column(name = "test_time")
    private Instant testTime;

    @ManyToOne
    @JsonIgnoreProperties(value = "userInCompleteTests", allowSetters = true)
    private SkillZagUser skillZagUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public UserInCompleteTest answer(String answer) {
        this.answer = answer;
        return this;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Instant getTestTime() {
        return testTime;
    }

    public UserInCompleteTest testTime(Instant testTime) {
        this.testTime = testTime;
        return this;
    }

    public void setTestTime(Instant testTime) {
        this.testTime = testTime;
    }

    public SkillZagUser getSkillZagUser() {
        return skillZagUser;
    }

    public UserInCompleteTest skillZagUser(SkillZagUser skillZagUser) {
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
        if (!(o instanceof UserInCompleteTest)) {
            return false;
        }
        return id != null && id.equals(((UserInCompleteTest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserInCompleteTest{" +
            "id=" + getId() +
            ", answer='" + getAnswer() + "'" +
            ", testTime='" + getTestTime() + "'" +
            "}";
    }
}
