package com.org.skillzag.assesment.service.mapper;


import com.org.skillzag.assesment.domain.*;
import com.org.skillzag.assesment.service.dto.SkillZagUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SkillZagUser} and its DTO {@link SkillZagUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SkillZagUserMapper extends EntityMapper<SkillZagUserDTO, SkillZagUser> {


    @Mapping(target = "userCompleteTests", ignore = true)
    @Mapping(target = "removeUserCompleteTest", ignore = true)
    @Mapping(target = "userInCompleteTests", ignore = true)
    @Mapping(target = "removeUserInCompleteTest", ignore = true)
    SkillZagUser toEntity(SkillZagUserDTO skillZagUserDTO);

    default SkillZagUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        SkillZagUser skillZagUser = new SkillZagUser();
        skillZagUser.setId(id);
        return skillZagUser;
    }
}
