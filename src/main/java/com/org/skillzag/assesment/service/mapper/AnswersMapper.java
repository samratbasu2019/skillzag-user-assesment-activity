package com.org.skillzag.assesment.service.mapper;


import com.org.skillzag.assesment.domain.Answers;
import com.org.skillzag.assesment.service.dto.AnswersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Answers} and its DTO {@link AnswersDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionsMapper.class})
public interface AnswersMapper extends EntityMapper<AnswersDTO, Answers> {

    @Mapping(source = "questions.id", target = "questionsId")
    AnswersDTO toDto(Answers answers);

    @Mapping(source = "questionsId", target = "questions")
    Answers toEntity(AnswersDTO answersDTO);

    default Answers fromId(Long id) {
        if (id == null) {
            return null;
        }
        Answers answers = new Answers();
        answers.setId(id);
        return answers;
    }
}
