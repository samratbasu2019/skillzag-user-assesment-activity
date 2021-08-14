package com.org.skillzag.assesment.service.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.org.skillzag.assesment.domain.UserInCompleteTest} entity.
 */
public class UserInCompleteTestDTO implements Serializable {

    private Long id;

    private String answer;

    private Instant testTime;


    private Long skillZagUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Instant getTestTime() {
        return testTime;
    }

    public void setTestTime(Instant testTime) {
        this.testTime = testTime;
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
        if (!(o instanceof UserInCompleteTestDTO)) {
            return false;
        }

        return id != null && id.equals(((UserInCompleteTestDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserInCompleteTestDTO{" +
            "id=" + getId() +
            ", answer='" + getAnswer() + "'" +
            ", testTime='" + getTestTime() + "'" +
            ", skillZagUserId=" + getSkillZagUserId() +
            "}";
    }
}
