package com.org.skillzag.assesment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class SkillzZagUserResponseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillzZagUserResponseDTO.class);
        SkillzZagUserResponseDTO skillzZagUserResponseDTO1 = new SkillzZagUserResponseDTO();
        skillzZagUserResponseDTO1.setId(1L);
        SkillzZagUserResponseDTO skillzZagUserResponseDTO2 = new SkillzZagUserResponseDTO();
        assertThat(skillzZagUserResponseDTO1).isNotEqualTo(skillzZagUserResponseDTO2);
        skillzZagUserResponseDTO2.setId(skillzZagUserResponseDTO1.getId());
        assertThat(skillzZagUserResponseDTO1).isEqualTo(skillzZagUserResponseDTO2);
        skillzZagUserResponseDTO2.setId(2L);
        assertThat(skillzZagUserResponseDTO1).isNotEqualTo(skillzZagUserResponseDTO2);
        skillzZagUserResponseDTO1.setId(null);
        assertThat(skillzZagUserResponseDTO1).isNotEqualTo(skillzZagUserResponseDTO2);
    }
}
