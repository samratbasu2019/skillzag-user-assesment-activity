package com.org.skillzag.assesment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswersMapperTest {

    private AnswersMapper answersMapper;

    @BeforeEach
    public void setUp() {
        answersMapper = new AnswersMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(answersMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(answersMapper.fromId(null)).isNull();
    }
}
