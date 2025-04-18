package org.example.vdcolataskscheduler.mapper;

import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperMyImpl {


    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setLogin( user.getLogin() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }


    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setLogin( userDto.getLogin() );
        user.setPassword( userDto.getPassword() );


        return user;
    }
}
