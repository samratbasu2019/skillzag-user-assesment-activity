package com.org.skillzag.assesment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class AnswersDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswersDTO.class);
        AnswersDTO answersDTO1 = new AnswersDTO();
        answersDTO1.setId(1L);
        AnswersDTO answersDTO2 = new AnswersDTO();
        assertThat(answersDTO1).isNotEqualTo(answersDTO2);
        answersDTO2.setId(answersDTO1.getId());
        assertThat(answersDTO1).isEqualTo(answersDTO2);
        answersDTO2.setId(2L);
        assertThat(answersDTO1).isNotEqualTo(answersDTO2);
        answersDTO1.setId(null);
        assertThat(answersDTO1).isNotEqualTo(answersDTO2);
    }
}
