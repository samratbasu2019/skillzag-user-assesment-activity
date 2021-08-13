package com.org.skillzag.assesment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.assesment.web.rest.TestUtil;

public class UserInCompleteTestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserInCompleteTest.class);
        UserInCompleteTest userInCompleteTest1 = new UserInCompleteTest();
        userInCompleteTest1.setId(1L);
        UserInCompleteTest userInCompleteTest2 = new UserInCompleteTest();
        userInCompleteTest2.setId(userInCompleteTest1.getId());
        assertThat(userInCompleteTest1).isEqualTo(userInCompleteTest2);
        userInCompleteTest2.setId(2L);
        assertThat(userInCompleteTest1).isNotEqualTo(userInCompleteTest2);
        userInCompleteTest1.setId(null);
        assertThat(userInCompleteTest1).isNotEqualTo(userInCompleteTest2);
    }
}
