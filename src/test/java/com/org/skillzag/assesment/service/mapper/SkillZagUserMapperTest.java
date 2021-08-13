package com.org.skillzag.assesment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SkillZagUserMapperTest {

    private SkillZagUserMapper skillZagUserMapper;

    @BeforeEach
    public void setUp() {
        skillZagUserMapper = new SkillZagUserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(skillZagUserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(skillZagUserMapper.fromId(null)).isNull();
    }
}
