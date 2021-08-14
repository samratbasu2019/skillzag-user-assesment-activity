package com.org.skillzag.assesment.service.mapper;


import com.org.skillzag.assesment.domain.QuestionSet;
import com.org.skillzag.assesment.service.dto.QuestionSetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link QuestionSet} and its DTO {@link QuestionSetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionSetMapper extends EntityMapper<QuestionSetDTO, QuestionSet> {


    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "removeQuestions", ignore = true)
    QuestionSet toEntity(QuestionSetDTO questionSetDTO);

    default QuestionSet fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuestionSet questionSet = new QuestionSet();
        questionSet.setId(id);
        return questionSet;
    }
}
