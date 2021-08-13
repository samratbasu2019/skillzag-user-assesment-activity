package com.org.skillzag.assesment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class UserInCompleteTestDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserInCompleteTestDTO.class);
        UserInCompleteTestDTO userInCompleteTestDTO1 = new UserInCompleteTestDTO();
        userInCompleteTestDTO1.setId(1L);
        UserInCompleteTestDTO userInCompleteTestDTO2 = new UserInCompleteTestDTO();
        assertThat(userInCompleteTestDTO1).isNotEqualTo(userInCompleteTestDTO2);
        userInCompleteTestDTO2.setId(userInCompleteTestDTO1.getId());
        assertThat(userInCompleteTestDTO1).isEqualTo(userInCompleteTestDTO2);
        userInCompleteTestDTO2.setId(2L);
        assertThat(userInCompleteTestDTO1).isNotEqualTo(userInCompleteTestDTO2);
        userInCompleteTestDTO1.setId(null);
        assertThat(userInCompleteTestDTO1).isNotEqualTo(userInCompleteTestDTO2);
    }
}
