package com.ahmed.job_portal.api.mapper;

import com.ahmed.job_portal.api.dto.UserDto;
import com.ahmed.job_portal.api.dto.UserResponseDto;
import com.ahmed.job_portal.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    UserResponseDto toUserResponseDto(User user);

    List<UserResponseDto> toUserResponseDto(List<User> users);
    User toUser(UserResponseDto userResponseDto);
}
