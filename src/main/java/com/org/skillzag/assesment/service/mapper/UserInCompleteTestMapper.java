package com.org.skillzag.assesment.service.mapper;


import com.org.skillzag.assesment.domain.UserInCompleteTest;
import com.org.skillzag.assesment.service.dto.UserInCompleteTestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link UserInCompleteTest} and its DTO {@link UserInCompleteTestDTO}.
 */
@Mapper(componentModel = "spring", uses = {SkillZagUserMapper.class})
public interface UserInCompleteTestMapper extends EntityMapper<UserInCompleteTestDTO, UserInCompleteTest> {

    @Mapping(source = "skillZagUser.id", target = "skillZagUserId")
    UserInCompleteTestDTO toDto(UserInCompleteTest userInCompleteTest);

    @Mapping(source = "skillZagUserId", target = "skillZagUser")
    UserInCompleteTest toEntity(UserInCompleteTestDTO userInCompleteTestDTO);

    default UserInCompleteTest fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserInCompleteTest userInCompleteTest = new UserInCompleteTest();
        userInCompleteTest.setId(id);
        return userInCompleteTest;
    }
}
