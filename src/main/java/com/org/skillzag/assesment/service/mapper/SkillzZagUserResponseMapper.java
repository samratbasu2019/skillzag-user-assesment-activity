package com.org.skillzag.assesment.service.mapper;


import com.org.skillzag.assesment.domain.*;
import com.org.skillzag.assesment.service.dto.SkillzZagUserResponseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SkillzZagUserResponse} and its DTO {@link SkillzZagUserResponseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SkillzZagUserResponseMapper extends EntityMapper<SkillzZagUserResponseDTO, SkillzZagUserResponse> {



    default SkillzZagUserResponse fromId(Long id) {
        if (id == null) {
            return null;
        }
        SkillzZagUserResponse skillzZagUserResponse = new SkillzZagUserResponse();
        skillzZagUserResponse.setId(id);
        return skillzZagUserResponse;
    }
}
