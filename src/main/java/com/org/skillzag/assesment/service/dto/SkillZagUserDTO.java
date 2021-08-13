package com.org.skillzag.assesment.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.org.skillzag.assesment.domain.SkillZagUser} entity.
 */
public class SkillZagUserDTO implements Serializable {
    
    private Long id;

    private Boolean isActive;

    private String userName;

    private String userRole;

    private Long questionSetId;

    private String others;

    private Instant lastCompleteTest;

    private Instant lastInCompleteTest;

    
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Instant getLastCompleteTest() {
        return lastCompleteTest;
    }

    public void setLastCompleteTest(Instant lastCompleteTest) {
        this.lastCompleteTest = lastCompleteTest;
    }

    public Instant getLastInCompleteTest() {
        return lastInCompleteTest;
    }

    public void setLastInCompleteTest(Instant lastInCompleteTest) {
        this.lastInCompleteTest = lastInCompleteTest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SkillZagUserDTO)) {
            return false;
        }

        return id != null && id.equals(((SkillZagUserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SkillZagUserDTO{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", userName='" + getUserName() + "'" +
            ", userRole='" + getUserRole() + "'" +
            ", questionSetId=" + getQuestionSetId() +
            ", others='" + getOthers() + "'" +
            ", lastCompleteTest='" + getLastCompleteTest() + "'" +
            ", lastInCompleteTest='" + getLastInCompleteTest() + "'" +
            "}";
    }
}
