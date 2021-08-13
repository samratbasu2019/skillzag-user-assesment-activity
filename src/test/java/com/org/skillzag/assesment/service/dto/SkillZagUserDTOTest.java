package com.org.skillzag.assesment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class SkillZagUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillZagUserDTO.class);
        SkillZagUserDTO skillZagUserDTO1 = new SkillZagUserDTO();
        skillZagUserDTO1.setId(1L);
        SkillZagUserDTO skillZagUserDTO2 = new SkillZagUserDTO();
        assertThat(skillZagUserDTO1).isNotEqualTo(skillZagUserDTO2);
        skillZagUserDTO2.setId(skillZagUserDTO1.getId());
        assertThat(skillZagUserDTO1).isEqualTo(skillZagUserDTO2);
        skillZagUserDTO2.setId(2L);
        assertThat(skillZagUserDTO1).isNotEqualTo(skillZagUserDTO2);
        skillZagUserDTO1.setId(null);
        assertThat(skillZagUserDTO1).isNotEqualTo(skillZagUserDTO2);
    }
}
