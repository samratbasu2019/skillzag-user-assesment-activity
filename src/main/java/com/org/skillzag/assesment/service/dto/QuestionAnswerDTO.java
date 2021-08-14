package com.org.skillzag.assesment.service.dto;

/**
 * A DTO for the {@link com.org.skillzag.assesment.domain.Answers} entity.
 */
public class QuestionAnswerDTO {

    private Long id;

    private Boolean isActive;

    private Boolean isCorrect;

    private String answer;

    private String question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
