package com.org.skillzag.assesment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class UserCompleteTestDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCompleteTestDTO.class);
        UserCompleteTestDTO userCompleteTestDTO1 = new UserCompleteTestDTO();
        userCompleteTestDTO1.setId(1L);
        UserCompleteTestDTO userCompleteTestDTO2 = new UserCompleteTestDTO();
        assertThat(userCompleteTestDTO1).isNotEqualTo(userCompleteTestDTO2);
        userCompleteTestDTO2.setId(userCompleteTestDTO1.getId());
        assertThat(userCompleteTestDTO1).isEqualTo(userCompleteTestDTO2);
        userCompleteTestDTO2.setId(2L);
        assertThat(userCompleteTestDTO1).isNotEqualTo(userCompleteTestDTO2);
        userCompleteTestDTO1.setId(null);
        assertThat(userCompleteTestDTO1).isNotEqualTo(userCompleteTestDTO2);
    }
}
