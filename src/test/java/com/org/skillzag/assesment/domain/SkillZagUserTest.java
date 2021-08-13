package com.org.skillzag.assesment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class SkillZagUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillZagUser.class);
        SkillZagUser skillZagUser1 = new SkillZagUser();
        skillZagUser1.setId(1L);
        SkillZagUser skillZagUser2 = new SkillZagUser();
        skillZagUser2.setId(skillZagUser1.getId());
        assertThat(skillZagUser1).isEqualTo(skillZagUser2);
        skillZagUser2.setId(2L);
        assertThat(skillZagUser1).isNotEqualTo(skillZagUser2);
        skillZagUser1.setId(null);
        assertThat(skillZagUser1).isNotEqualTo(skillZagUser2);
    }
}
