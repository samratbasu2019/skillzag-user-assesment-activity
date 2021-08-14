package com.org.skillzag.assesment.service.mapper;


import com.org.skillzag.assesment.domain.*;
import com.org.skillzag.assesment.service.dto.QuestionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Questions} and its DTO {@link QuestionsDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionSetMapper.class})
public interface QuestionsMapper extends EntityMapper<QuestionsDTO, Questions> {

    @Mapping(source = "questionSet.id", target = "questionSetId")
    QuestionsDTO toDto(Questions questions);

    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "removeAnswers", ignore = true)
    @Mapping(source = "questionSetId", target = "questionSet")
    Questions toEntity(QuestionsDTO questionsDTO);

    default Questions fromId(Long id) {
        if (id == null) {
            return null;
        }
        Questions questions = new Questions();
        questions.setId(id);
        return questions;
    }
}
