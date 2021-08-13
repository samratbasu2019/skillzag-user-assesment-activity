package com.org.skillzag.assesment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserInCompleteTestMapperTest {

    private UserInCompleteTestMapper userInCompleteTestMapper;

    @BeforeEach
    public void setUp() {
        userInCompleteTestMapper = new UserInCompleteTestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userInCompleteTestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userInCompleteTestMapper.fromId(null)).isNull();
    }
}
