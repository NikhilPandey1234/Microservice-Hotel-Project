package com.userSer.util;

import com.userSer.dto.UserDTO;
import com.userSer.modal.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMappers {

    @Autowired
    private ModelMapper modelMapper;

  public User userDtoToUserEntity(UserDTO userDTO){
      User user = this.modelMapper.map(userDTO, User.class);
      return user;
  }

  public UserDTO userEntityToUserDto(User user){
      UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
      return userDTO;
  }
}
