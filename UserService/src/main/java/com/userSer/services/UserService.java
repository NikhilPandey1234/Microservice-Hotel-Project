package com.userSer.services;

import com.userSer.dto.UserDTO;
import com.userSer.util.ApiResponse;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, String userId);

    List<UserDTO> getAllUser();

    UserDTO getUSerById(String userId);

    ApiResponse deleteUser(String userId);


}
