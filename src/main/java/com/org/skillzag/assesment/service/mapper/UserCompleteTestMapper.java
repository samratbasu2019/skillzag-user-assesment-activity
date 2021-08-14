package com.org.skillzag.assesment.service.mapper;


import com.org.skillzag.assesment.domain.*;
import com.org.skillzag.assesment.service.dto.UserCompleteTestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserCompleteTest} and its DTO {@link UserCompleteTestDTO}.
 */
@Mapper(componentModel = "spring", uses = {SkillZagUserMapper.class})
public interface UserCompleteTestMapper extends EntityMapper<UserCompleteTestDTO, UserCompleteTest> {

    @Mapping(source = "skillZagUser.id", target = "skillZagUserId")
    UserCompleteTestDTO toDto(UserCompleteTest userCompleteTest);

    @Mapping(source = "skillZagUserId", target = "skillZagUser")
    UserCompleteTest toEntity(UserCompleteTestDTO userCompleteTestDTO);

    default UserCompleteTest fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserCompleteTest userCompleteTest = new UserCompleteTest();
        userCompleteTest.setId(id);
        return userCompleteTest;
    }
}
