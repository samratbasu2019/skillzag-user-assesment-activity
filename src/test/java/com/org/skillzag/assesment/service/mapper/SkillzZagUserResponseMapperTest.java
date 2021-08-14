package com.org.skillzag.assesment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SkillzZagUserResponseMapperTest {

    private SkillzZagUserResponseMapper skillzZagUserResponseMapper;

    @BeforeEach
    public void setUp() {
        skillzZagUserResponseMapper = new SkillzZagUserResponseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(skillzZagUserResponseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(skillzZagUserResponseMapper.fromId(null)).isNull();
    }
}
