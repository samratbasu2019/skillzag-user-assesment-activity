package com.org.skillzag.assesment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class SkillzZagUserResponseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillzZagUserResponse.class);
        SkillzZagUserResponse skillzZagUserResponse1 = new SkillzZagUserResponse();
        skillzZagUserResponse1.setId(1L);
        SkillzZagUserResponse skillzZagUserResponse2 = new SkillzZagUserResponse();
        skillzZagUserResponse2.setId(skillzZagUserResponse1.getId());
        assertThat(skillzZagUserResponse1).isEqualTo(skillzZagUserResponse2);
        skillzZagUserResponse2.setId(2L);
        assertThat(skillzZagUserResponse1).isNotEqualTo(skillzZagUserResponse2);
        skillzZagUserResponse1.setId(null);
        assertThat(skillzZagUserResponse1).isNotEqualTo(skillzZagUserResponse2);
    }
}
