package com.org.skillzag.assesment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class UserCompleteTestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCompleteTest.class);
        UserCompleteTest userCompleteTest1 = new UserCompleteTest();
        userCompleteTest1.setId(1L);
        UserCompleteTest userCompleteTest2 = new UserCompleteTest();
        userCompleteTest2.setId(userCompleteTest1.getId());
        assertThat(userCompleteTest1).isEqualTo(userCompleteTest2);
        userCompleteTest2.setId(2L);
        assertThat(userCompleteTest1).isNotEqualTo(userCompleteTest2);
        userCompleteTest1.setId(null);
        assertThat(userCompleteTest1).isNotEqualTo(userCompleteTest2);
    }
}
