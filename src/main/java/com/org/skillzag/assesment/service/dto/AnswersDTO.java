package com.org.skillzag.assesment.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.org.skillzag.assesment.domain.Answers} entity.
 */
public class AnswersDTO implements Serializable {
    
    private Long id;

    private Boolean isActive;

    private Boolean isCorrect;

    private String answer;


    private Long questionsId;
    
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

    public Boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(Long questionsId) {
        this.questionsId = questionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswersDTO)) {
            return false;
        }

        return id != null && id.equals(((AnswersDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswersDTO{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", isCorrect='" + isIsCorrect() + "'" +
            ", answer='" + getAnswer() + "'" +
            ", questionsId=" + getQuestionsId() +
            "}";
    }
}
