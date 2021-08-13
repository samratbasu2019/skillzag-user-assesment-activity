package com.org.skillzag.assesment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserCompleteTestMapperTest {

    private UserCompleteTestMapper userCompleteTestMapper;

    @BeforeEach
    public void setUp() {
        userCompleteTestMapper = new UserCompleteTestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userCompleteTestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userCompleteTestMapper.fromId(null)).isNull();
    }
}
